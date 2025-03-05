package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.Lottos
import lotto.domain.model.PurchaseAmount
import lotto.domain.model.PurchaseCount
import lotto.domain.model.WinningNumbers
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
            retryEvent { PurchaseAmount.from(requireNotNull(inputView.readPurchaseAmount()) { INVALID_TO_NUMBER }) }
        val lottos = purchaseLotto(purchaseAmount)
        val winningNumbers = getWinningNumbers()

        printWinningResults(lottos, winningNumbers)
    }

    private fun purchaseLotto(purchaseAmount: PurchaseAmount): Lottos {
        val (manualPurchaseCount, remainPurchaseCount) =
            retryEvent {
                val manualPurchaseCount =
                    PurchaseCount(requireNotNull(inputView.readManualLottoCount()) { INVALID_TO_NUMBER })
                val currentPurchaseAmount = purchaseAmount.purchaseLotto(manualPurchaseCount.count)
                val remainPurchaseCount = currentPurchaseAmount.getRemainPurchaseCount()
                manualPurchaseCount.count to remainPurchaseCount
            }
        val lottos = getLottos(manualPurchaseCount, remainPurchaseCount)
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
            List(purchaseManualLottoCount) { retryEvent { Lotto(requireNotNull(inputView.readLottoNumbers()) { INVALID_TO_NUMBER }) } }
        val randomLottos = List(purchaseRandomLottoCount) { lottoMachine.generate() }
        return Lottos(manualLottos, randomLottos)
    }

    private fun getWinningNumbers(): WinningNumbers {
        outputView.printWinningNumbers()
        val winningLotto = Lotto(retryEvent { requireNotNull(inputView.readLottoNumbers()) { INVALID_TO_NUMBER } })
        outputView.printBonusNumber()
        return retryEvent {
            val bonusNumber = LottoNumber(requireNotNull(inputView.readBonusNumber()) { INVALID_TO_NUMBER })
            WinningNumbers(winningLotto, bonusNumber)
        }
    }

    private fun printWinningResults(
        lottos: Lottos,
        winningNumbers: WinningNumbers,
    ) {
        val lottoRanks = winningNumbers.calculateLottoRanks(lottos)
        outputView.printWinningResults(lottoRanks)
        outputView.printTotalReturns(lottoRanks.calculateTotalReturn())
    }

    private fun <T> retryEvent(event: () -> T): T {
        while (true) {
            runCatching { event() }
                .onSuccess { return it }
                .onFailure { outputView.printErrorMessage(it.message ?: it.stackTraceToString()) }
        }
    }

    companion object {
        private const val INVALID_TO_NUMBER = "숫자를 입력해주세요"
    }
}
