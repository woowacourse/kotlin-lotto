package lotto.controller

import lotto.model.Amount
import lotto.model.LottoCashier
import lotto.model.LottoMarket.Companion.EMPTY_LOTTO_QUANTITY
import lotto.model.LottoProfitCalculator
import lotto.model.LottoWallet
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

        val lottoWallet = LottoCashier().buyLottos(purchaseAmount, manualLottoNumbers)

        outputView.printPurchaseLottoQuantity(
            manualLottoNumbers.size,
            purchaseAmount.getAutoLottoQuantity(manualLottoNumbers.size),
        )
        outputView.printLotto(lottoWallet.lottos)

        val winningDiscriminator = getWinningInfo()
        discriminateLottos(winningDiscriminator, lottoWallet, purchaseAmount)
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

    private fun getWinningInfo(): WinningDiscriminator {
        val winningNumbers = inputView.readWinningLottoNumbers()
        val bonusNumber = inputView.readBonusNumber()

        return WinningDiscriminator(winningNumbers, bonusNumber)
    }

    private fun discriminateLottos(
        winningDiscriminator: WinningDiscriminator,
        lottoWallet: LottoWallet,
        purchaseAmount: Amount,
    ) {
        val winningResult = winningDiscriminator.getResult(lottoWallet)
        outputView.printWinningLottoResult(winningResult)

        val lottoProfitCalculator = LottoProfitCalculator()
        val profitResult = lottoProfitCalculator.getProfitResult(winningResult, purchaseAmount)

        outputView.printProfitRate(profitResult.profitRate, profitResult.profitStatus)
    }
}
