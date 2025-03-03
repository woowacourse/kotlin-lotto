package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoNumberResult
import lotto.domain.model.LottoResult
import lotto.domain.model.Lottos
import lotto.domain.model.PurchaseAmount
import lotto.domain.model.PurchaseAmountResult
import lotto.domain.model.PurchaseCount
import lotto.domain.model.PurchaseCountResult
import lotto.domain.model.WinningNumbers
import lotto.domain.model.WinningNumbersResult
import lotto.domain.service.LottoMachine
import lotto.domain.service.RandomLottoMachine
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lottoMachine: LottoMachine = RandomLottoMachine(),
) {
    fun run() {
        val purchaseAmount =
            retryHandleResult { handlePurchaseAmountResult(PurchaseAmount.from(inputView.readPurchaseAmount())) }
        val lottos = purchaseLotto(purchaseAmount)
        val winningNumbers = getWinningNumbers()

        printWinningResults(lottos, winningNumbers)
    }

    private fun purchaseLotto(purchaseAmount: PurchaseAmount): Lottos {
        val lottos =
            retryHandleResult {
                val totalPurchaseCount =
                    handlePurchaseCount(PurchaseCount.from(purchaseAmount.getRemainPurchaseCount()))
                val manualPurchaseCount = handlePurchaseCount(PurchaseCount.from(inputView.readManualLottoCount()))
                val currentPurchaseAmount =
                    handlePurchaseAmountResult(purchaseAmount.purchaseLotto(manualPurchaseCount.count))
                val remainPurchaseCount = currentPurchaseAmount.getRemainPurchaseCount()
                val purchaseRandomLottoCount =
                    handlePurchaseCount(totalPurchaseCount.getRemainPurchaseCount(remainPurchaseCount))
                getLottos(manualPurchaseCount.count, purchaseRandomLottoCount.count)
            }
        outputView.printPurchaseLottoCount(lottos.getManualLottosCount(), lottos.getRandomLottosCount())
        lottos.lottos.forEach { lotto -> outputView.printPurchaseLottoNumbers(lotto.numbers) }
        return lottos
    }

    private fun getLottos(
        purchaseManualLottoCount: Int,
        purchaseRandomLottoCount: Int,
    ): Lottos {
        outputView.printManualLottoNumbers()
        val manualLottos =
            List(purchaseManualLottoCount) { retryHandleResult { handleLottoResult(Lotto.from(inputView.readLottoNumbers())) } }
        val randomLottos = List(purchaseRandomLottoCount) { lottoMachine.generate() }
        return Lottos(manualLottos, randomLottos)
    }

    private fun getWinningNumbers(): WinningNumbers {
        outputView.printWinningNumbers()
        val winningLotto = retryHandleResult { handleLottoResult(Lotto.from(inputView.readLottoNumbers())) }
        outputView.printBonusNumber()
        val bonusNumber = retryHandleResult { handleLottoNumberResult(LottoNumber.from(inputView.readBonusNumber())) }
        return retryHandleResult { handleWinningNumbersLottoHandle(WinningNumbers.from(winningLotto, bonusNumber)) }
    }

    private fun printWinningResults(
        lottos: Lottos,
        winningNumbers: WinningNumbers,
    ) {
        val lottoRanks = winningNumbers.calculateLottoRanks(lottos)
        outputView.printWinningResults(lottoRanks)
        outputView.printTotalReturns(lottoRanks.calculateTotalReturn())
    }

    private fun handlePurchaseCount(result: PurchaseCountResult): PurchaseCount {
        return when (result) {
            PurchaseCountResult.InvalidCountNull -> throw IllegalArgumentException(INVALID_NUMBER_MESSAGE)
            is PurchaseCountResult.InvalidCountRange -> throw IllegalArgumentException(INVALID_MANUAL_COUNT)
            is PurchaseCountResult.PurchaseFail -> throw IllegalArgumentException(
                INVALID_PURCHASE_FAIL.format(
                    result.purchaseCount,
                    result.count,
                ),
            )

            is PurchaseCountResult.Success -> result.purchaseCount
        }
    }

    private fun handleLottoNumberResult(result: LottoNumberResult): LottoNumber {
        return when (result) {
            LottoNumberResult.InvalidNumberNull -> throw IllegalArgumentException(INVALID_NUMBER_MESSAGE)
            is LottoNumberResult.InvalidNumberRange -> throw IllegalArgumentException(
                INVALID_LOTTO_NUMBER_RANGE_MESSAGE.format(
                    result.number,
                ),
            )

            is LottoNumberResult.Success -> result.lottoNumber
        }
    }

    private fun handleLottoResult(result: LottoResult): Lotto {
        return when (result) {
            is LottoResult.Success -> result.lotto
            LottoResult.InvalidNumberNull -> throw IllegalArgumentException(INVALID_NUMBER_MESSAGE)
            is LottoResult.InvalidNumberRange -> throw IllegalArgumentException(
                INVALID_LOTTO_NUMBER_RANGE_MESSAGE.format(
                    result.lottoNumber,
                ),
            )

            LottoResult.InvalidNumbersNull -> throw IllegalArgumentException(
                INVALID_LOTTO_NUMBER_RANGE_MESSAGE.format(
                    result,
                ),
            )

            is LottoResult.InvalidNumbersSize -> throw IllegalArgumentException(
                INVALID_LOTTO_NUMBER_SIZE_MESSAGE.format(
                    result.lottoNumbers,
                ),
            )
        }
    }

    private fun handlePurchaseAmountResult(result: PurchaseAmountResult): PurchaseAmount {
        return when (result) {
            is PurchaseAmountResult.InvalidAmount -> throw IllegalArgumentException(
                INVALID_MIN_AMOUNT_MESSAGE.format(
                    result.amount,
                ),
            )

            PurchaseAmountResult.InvalidAmountNull -> throw IllegalArgumentException(INVALID_NUMBER_MESSAGE)
            is PurchaseAmountResult.InvalidPurchase -> throw IllegalArgumentException(
                INVALID_PURCHASE_LOTTO_AMOUNT_MESSAGE.format(result.purchaseAmount, result.currentAmount),
            )

            is PurchaseAmountResult.Success -> result.purchaseAmount
        }
    }

    private fun handleWinningNumbersLottoHandle(result: WinningNumbersResult): WinningNumbers {
        return when (result) {
            is WinningNumbersResult.InvalidHasBonusNumber -> throw IllegalArgumentException(
                DUPLICATE_WINNING_NUMBER_MESSAGE,
            )

            is WinningNumbersResult.Success -> result.winningNumbers
        }
    }

    private fun <T> retryHandleResult(handleResult: () -> T): T {
        while (true) {
            runCatching {
                handleResult()
            }.onSuccess { return it }.onFailure { outputView.printErrorMessage(it.message ?: it.stackTraceToString()) }
        }
    }

    companion object {
        private const val INVALID_MANUAL_COUNT = "수동으로 입력 받을 숫자는 음수가 될 수 없습니다"
        private const val INVALID_LOTTO_NUMBER_RANGE_MESSAGE = "로또의 번호가 %s 입니다. 로또의 각 번호는 1~45이하의 숫자만 가집니다."
        private const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "%s 중복을 제외한 로또 번호 입니다. 로또 번호는 6개여야 합니다."
        private const val INVALID_MIN_AMOUNT_MESSAGE = "%s원으로 로또를 구매하지 못했습니다 로또는 한 장 이상 구매해야 합니다."
        private const val INVALID_NUMBER_MESSAGE = "숫자만 입력해 주세요."
        private const val INVALID_PURCHASE_FAIL = "구매하시는 개수%s는 구매할 수 있는 개수%s 보다 작아야 합니다. "
        private const val INVALID_PURCHASE_LOTTO_AMOUNT_MESSAGE =
            "선택하신 개수의 금액은 %d 입니다. 구매하시는 금액은 현재 구매할 %d원 보다 적어야 합니다."
        private const val DUPLICATE_WINNING_NUMBER_MESSAGE = "보너스 번호 %s은(는) 당첨 번호 %s와 중복 될 수 없습니다."
    }
}
