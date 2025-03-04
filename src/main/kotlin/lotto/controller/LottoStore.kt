package lotto.controller

import lotto.domain.generator.AutoLottoGenerator
import lotto.domain.generator.ManualLottoGenerator
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket
import lotto.domain.model.PurchaseAmount
import lotto.domain.model.PurchaseCount
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoStore(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val purchaseCount = getLottoCount()
        val lottoTickets = generateLottoTickets(purchaseCount.manualCount, purchaseCount.autoCount)
        outputView.printLotto(lottoTickets)

        val winningLotto = getWinningLotto()

        val result = calculateResult(lottoTickets, winningLotto)
        outputView.printResult(result.ranks)
        outputView.printProfit(result.calculateProfit())
    }

    private fun getLottoCount(): PurchaseCount {
        var purchaseAmount: PurchaseAmount? = null
        while (purchaseAmount == null) {
            purchaseAmount = inputView.inputPurchaseAmount()
        }
        val totalCount: Int = purchaseAmount.calculatePurchaseLottoCount()
        val manualCount: Int = inputView.inputManualCount()
        return PurchaseCount.from(totalCount, manualCount)
    }

    private fun generateLottoTickets(
        manualCount: Int,
        autoCount: Int,
    ): List<LottoTicket> {
        if (manualCount != 0) outputView.printManualNumbersGuide()
        val manualTickets = List(manualCount) { ManualLottoGenerator(inputView.inputManualNumbers()).generateLotto() }
        val autoTickets = List(autoCount) { AutoLottoGenerator().generateLotto() }
        outputView.printPurchaseCount(manualCount, autoCount)
        return manualTickets + autoTickets
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = WinningLotto(inputView.inputWinningNumbers())
        val bonusNumber = LottoNumber(inputView.inputBonusNumber())
        return WinningLotto(winningNumbers.winningNumbers, bonusNumber)
    }

    private fun calculateResult(
        lottoTickets: List<LottoTicket>,
        winningLotto: WinningLotto,
    ): LottoResult = LottoResult.calculateResult(lottoTickets, winningLotto)
}
