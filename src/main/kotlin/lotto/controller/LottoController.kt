package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoNumberResult
import lotto.domain.model.LottoResult
import lotto.domain.model.Lottos
import lotto.domain.model.PurchaseAmount
import lotto.domain.model.PurchaseAmountResult
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
        val manualCount = retryHandleResult { handleManualCount(inputView.readManualLottoCount()) }
        val purchaseManualLottoCount = purchaseAmount.getPurchaseLottoCount(manualCount)
        val lottos = getLottos(purchaseAmount, purchaseManualLottoCount)
        outputView.printPurchaseLottoCount(lottos.getManualLottosSize(), lottos.getRandomLottosSize())
        lottos.lottos.forEach { lotto -> outputView.printPurchaseLottoNumbers(lotto.numbers) }
        return lottos
    }

    private fun getLottos(
        purchaseAmount: PurchaseAmount,
        purchaseManualLottoCount: Int,
    ): Lottos {
        val lottos = Lottos()
        outputView.printManualLottoNumbers()
        repeat(purchaseManualLottoCount) {
            val manualLotto = retryHandleResult { handleLottoResult(Lotto.from(inputView.readLottoNumbers())) }
            lottos.addManualLotto(manualLotto)
        }
        repeat(purchaseAmount.getPurchaseRemainLottoCount()) { lottos.addRandomLotto(lottoMachine.generate()) }
        return lottos
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

    private fun handleManualCount(count: Int?): Int? {
        when {
            count == null -> {
                outputView.printErrorMessage(INVALID_NUMBER_MESSAGE)
                return null
            }

            count < 0 -> {
                outputView.printErrorMessage(INVALID_MANUAL_COUNT)
                return null
            }
        }
        return count
    }

    private fun handleLottoNumberResult(result: LottoNumberResult): LottoNumber? {
        return when (result) {
            LottoNumberResult.InvalidNumberNull -> {
                outputView.printErrorMessage(INVALID_NUMBER_MESSAGE)
                null
            }

            is LottoNumberResult.InvalidNumberRange -> {
                outputView.printErrorMessage(INVALID_LOTTO_NUMBER_RANGE_MESSAGE.format(result.number))
                null
            }

            is LottoNumberResult.Success -> result.lottoNumber
        }
    }

    private fun handleLottoResult(result: LottoResult): Lotto? {
        return when (result) {
            is LottoResult.Success -> result.lotto
            LottoResult.InvalidNumberNull -> {
                outputView.printErrorMessage(INVALID_NUMBER_MESSAGE)
                null
            }

            is LottoResult.InvalidNumberRange -> {
                outputView.printErrorMessage(INVALID_LOTTO_NUMBER_RANGE_MESSAGE.format(result.lottoNumber))
                null
            }

            LottoResult.InvalidNumbersNull -> {
                outputView.printErrorMessage(
                    INVALID_LOTTO_NUMBER_RANGE_MESSAGE.format(
                        result,
                    ),
                )
                null
            }

            is LottoResult.InvalidNumbersSize -> {
                outputView.printErrorMessage(
                    INVALID_LOTTO_NUMBER_SIZE_MESSAGE.format(
                        result.lottoNumbers,
                    ),
                )
                null
            }
        }
    }

    private fun handlePurchaseAmountResult(result: PurchaseAmountResult): PurchaseAmount? {
        return when (result) {
            is PurchaseAmountResult.InvalidAmount -> {
                outputView.printErrorMessage(INVALID_MIN_AMOUNT_MESSAGE.format(result.amount))
                null
            }

            PurchaseAmountResult.InvalidAmountNull -> {
                outputView.printErrorMessage(INVALID_NUMBER_MESSAGE)
                null
            }

            is PurchaseAmountResult.Success -> result.purchaseAmount
        }
    }

    private fun handleWinningNumbersLottoHandle(result: WinningNumbersResult): WinningNumbers? {
        return when (result) {
            is WinningNumbersResult.InvalidHasBonusNumber -> {
                outputView.printErrorMessage(DUPLICATE_WINNING_NUMBER_MESSAGE)
                null
            }

            is WinningNumbersResult.Success -> result.winningNumbers
        }
    }

    private fun <T> retryHandleResult(handleResult: () -> T?): T {
        while (true) {
            val result = handleResult()
            if (result != null) return result
        }
    }

    companion object {
        private const val INVALID_MANUAL_COUNT = "수동으로 입력 받을 숫자는 음수가 될 수 없습니다"
        private const val INVALID_LOTTO_NUMBER_RANGE_MESSAGE = "로또의 번호가 %s 입니다. 로또의 각 번호는 1~45이하의 숫자만 가집니다."
        private const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "%s 중복을 제외한 로또 번호 입니다. 로또 번호는 6개여야 합니다."
        private const val INVALID_MIN_AMOUNT_MESSAGE = "%s원으로 로또를 구매하지 못했습니다 로또는 한 장 이상 구매해야 합니다."
        private const val INVALID_NUMBER_MESSAGE = "숫자만 입력해 주세요."
        private const val DUPLICATE_WINNING_NUMBER_MESSAGE = "보너스 번호 %s은(는) 당첨 번호 %s와 중복 될 수 없습니다."
    }
}
