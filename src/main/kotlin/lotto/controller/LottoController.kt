package lotto.controller

import lotto.model.Amount
import lotto.model.AutoLottoMachine
import lotto.model.LottoMarket
import lotto.model.LottoMarket.Companion.EMPTY_LOTTO_QUANTITY
import lotto.model.LottoProfitCalculator
import lotto.model.LottoWallet
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
        val lottoWallet = getLottoWallet(purchaseAmount, manualLottoNumbers)

        outputView.printPurchaseLottoQuantity(
            manualLottoNumbers.size,
            purchaseAmount.getAutoLottoQuantity(manualLottoNumbers.size),
        )
        outputView.printLotto(lottoWallet.lottos)

        val winningResult = getWinningResult(lottoWallet)
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

    private fun getLottoWallet(
        purchaseAmount: Amount,
        manualLottoNumbers: List<List<Int>>,
    ): LottoWallet {
        val manualLottoMachine = ManualLottoMachine()
        val autoLottoMachine = AutoLottoMachine()

        val lottoMarket = LottoMarket(purchaseAmount, manualLottoNumbers)
        val lottoWallet = lottoMarket.buy(manualLottoMachine, autoLottoMachine)

        return lottoWallet
    }

    private fun getWinningResult(lottoWallet: LottoWallet): Map<Rank, Int> {
        val winningNumbers = inputView.readWinningLottoNumbers()
        val bonusNumber = inputView.readBonusNumber()

        return WinningDiscriminator(winningNumbers, bonusNumber).getResult(lottoWallet)
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
