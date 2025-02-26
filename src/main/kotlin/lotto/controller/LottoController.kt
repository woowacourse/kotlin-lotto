package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.Lottos
import lotto.domain.model.PurchaseAmount
import lotto.domain.model.WinningNumbers
import lotto.domain.service.LottoMachine
import lotto.domain.service.LottoMachineImpl
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lottoMachine: LottoMachine = LottoMachineImpl(),
) {
    fun run() {
        val purchaseAmount = PurchaseAmount(inputView.readPurchaseAmount())
        val lottos = purchaseLotto(purchaseAmount)
        val winningNumbers = getWinningNumbers()

        printWinningResults(lottos, winningNumbers)
    }

    private fun purchaseLotto(purchaseAmount: PurchaseAmount): Lottos {
        val manualCount = inputView.readManualLottoCount()
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
            val lotto = lottoMachine.generateManualLottoNumbers(inputView.readLottoNumbers())
            lottos.addManualLotto(lotto)
        }
        repeat(purchaseAmount.getPurchaseRemainLottoCount()) {
            val lotto = lottoMachine.generateRandomLottoNumbers()
            lottos.addRandomLotto(lotto)
        }
        return lottos
    }

    private fun getWinningNumbers(): WinningNumbers {
        outputView.printWinningNumbers()
        val winningLotto = Lotto(inputView.readLottoNumbers())
        outputView.printBonusNumber()
        val bonusNumber = LottoNumber(inputView.readBonusNumber())
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun printWinningResults(
        lottos: Lottos,
        winningNumbers: WinningNumbers,
    ) {
        val lottoRanks = winningNumbers.calculateLottoRanks(lottos)
        outputView.printWinningResults(lottoRanks)
        outputView.printTotalReturns(lottoRanks.calculateTotalReturn())
    }
}
