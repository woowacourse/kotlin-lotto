package lotto.controller

import lotto.model.Amount
import lotto.model.AutoLottoMachine
import lotto.model.Lotto
import lotto.model.LottoMarket
import lotto.model.LottoMarket.Companion.EMPTY_LOTTO_QUANTITY
import lotto.model.LottoProfitCalculator
import lotto.model.ManualLottoMachine
import lotto.model.Rank
import lotto.model.WinningDiscriminator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val manualLottoNumbers = getManualLottoNumbers()
        val lottos = getLottos(purchaseAmount, manualLottoNumbers)

        outputView.printPurchaseLottoQuantity(
            manualLottoNumbers.size,
            purchaseAmount.getAutoLottoQuantity(manualLottoNumbers.size),
        )
        outputView.printLotto(lottos)

        val winningResult = getWinningResult(lottos)
        getProfitResult(winningResult, purchaseAmount)
    }

    private fun getPurchaseAmount(): Amount {
        val purchaseAmount = inputView.readPurchaseAmount()
        return Amount(purchaseAmount)
    }

    private fun getManualLottoNumbers(): List<List<Int>> {
        val manualLottoQuantity = inputView.readManualLottoQuantity()

        if (manualLottoQuantity <= EMPTY_LOTTO_QUANTITY) return emptyList()

        return List(manualLottoQuantity) { inputView.readManualLottoNumbers() }
    }

    private fun getLottos(
        purchaseAmount: Amount,
        manualLottoNumbers: List<List<Int>>,
    ): List<Lotto> {
        val manualLottoMachine = ManualLottoMachine()
        val autoLottoMachine = AutoLottoMachine()

        val lottoMarket = LottoMarket(purchaseAmount, manualLottoNumbers)
        val lottos = lottoMarket.buy(manualLottoMachine, autoLottoMachine)

        return lottos
    }

    private fun getWinningResult(lottos: List<Lotto>): Map<Rank, Int> {
        val winningNumbers = inputView.readWinningLottoNumbers()
        val bonusNumber = inputView.readBonusNumber()

        return WinningDiscriminator(winningNumbers, bonusNumber).getResult(lottos)
    }

    private fun getProfitResult(
        winningResult: Map<Rank, Int>,
        purchaseAmount: Amount,
    ) {
        outputView.printWinningLottoResult(winningResult)

        val lottoProfitCalculator = LottoProfitCalculator()
        val profitResult = lottoProfitCalculator.getProfitResult(winningResult, purchaseAmount)

        outputView.printProfitRate(profitResult.profitRate, profitResult.profitStatus)
    }
}
