package lotto.controller

import lotto.model.Amount
import lotto.model.AutoLottoMachine
import lotto.model.LottoMarket
import lotto.model.LottoMarket.Companion.EMPTY_LOTTO_QUANTITY
import lotto.model.LottoProfitCalculator
import lotto.model.LottoWallet
import lotto.model.ManualLottoMachine
import lotto.model.ProfitStatus
import lotto.model.WinningDiscriminator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val manualLottoQuantity = getManualQuantity()

        val lottoMarket = purchaseLottos(purchaseAmount, manualLottoQuantity)
        val lottoWallet = storeLottos(lottoMarket, manualLottoQuantity)

        val winningDiscriminator = getWinningInfo()
        discriminateLottos(winningDiscriminator, lottoWallet, purchaseAmount)
    }

    private fun getPurchaseAmount(): Amount {
        outputView.printPurchaseAmountGuide()
        val purchaseAmount = inputView.readPurchaseAmount()
        return Amount(purchaseAmount)
    }

    private fun getManualQuantity(): Int {
        outputView.printManualLottoQuantityGuide()
        val manualLottoQuantity = inputView.readManualLottoQuantity()
        return manualLottoQuantity
    }

    private fun purchaseLottos(
        purchaseAmount: Amount,
        manualLottoQuantity: Int,
    ): LottoMarket {
        val lottoMarket = LottoMarket(purchaseAmount, manualLottoQuantity)
        outputView.printManualLottoNumbersGuide(manualLottoQuantity > EMPTY_LOTTO_QUANTITY)

        return lottoMarket
    }

    private fun storeLottos(
        lottoMarket: LottoMarket,
        manualLottoQuantity: Int,
    ): LottoWallet {
        val lottoWallet = LottoWallet()

        if (manualLottoQuantity > EMPTY_LOTTO_QUANTITY) {
            val manualLottoMachine = ManualLottoMachine()
            val manualNumbers = List(manualLottoQuantity) { inputView.readLottoNumbers() }
            lottoMarket.buy(manualLottoMachine, manualNumbers, lottoWallet)
        }

        val autoLottoMachine = AutoLottoMachine()
        lottoMarket.buy(autoLottoMachine, emptyList(), lottoWallet)

        outputView.printPurchaseLottoQuantity(manualLottoQuantity, lottoMarket.autoLottoQuantity)
        outputView.printLotto(lottoWallet.lottos)
        return lottoWallet
    }

    private fun discriminateLottos(
        winningDiscriminator: WinningDiscriminator,
        lottoWallet: LottoWallet,
        purchaseAmount: Amount,
    ) {
        val winningResult = winningDiscriminator.getResult(lottoWallet)
        outputView.printWinningResultTitle()
        outputView.printWinningLottoResult(winningResult)

        val lottoProfitCalculator = LottoProfitCalculator()
        val profitRate = lottoProfitCalculator.getProfitRate(winningResult, purchaseAmount)
        val profitStatus = ProfitStatus.from(profitRate)
        outputView.printProfitRate(profitRate, profitStatus)
    }

    private fun getWinningInfo(): WinningDiscriminator {
        outputView.printWinningNumbersGuide()
        val winningNumbers = inputView.readLottoNumbers()

        outputView.printBonusNumberGuide()
        val bonusNumber = inputView.readBonusNumber()

        val winningDiscriminator = WinningDiscriminator(winningNumbers, bonusNumber)
        return winningDiscriminator
    }
}
