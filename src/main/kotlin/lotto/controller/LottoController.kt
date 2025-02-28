package lotto.controller

import lotto.model.LottoMachine
import lotto.model.LottoMachine.Companion.EMPTY_LOTTO_QUANTITY
import lotto.model.LottoProfitCalculator
import lotto.model.LottoStore
import lotto.model.LottoWallet
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

        val lottoStore = purchaseLottos(purchaseAmount, manualLottoQuantity)
        val lottoWallet = storeLottos(lottoStore, manualLottoQuantity)

        val winningDiscriminator = getWinningInfo()
        discriminateLottos(winningDiscriminator, lottoWallet, purchaseAmount)
    }

    private fun getPurchaseAmount(): Int {
        outputView.printPurchaseAmountGuide()
        val purchaseAmount = inputView.readPurchaseAmount()
        return purchaseAmount
    }

    private fun getManualQuantity(): Int {
        outputView.printManualLottoQuantityGuide()
        val manualLottoQuantity = inputView.readManualLottoQuantity()
        return manualLottoQuantity
    }

    private fun purchaseLottos(
        purchaseAmount: Int,
        manualLottoQuantity: Int,
    ): LottoStore {
        val lottoMachine = LottoMachine()
        val lottoStore = LottoStore(purchaseAmount, manualLottoQuantity, lottoMachine)
        outputView.printManualLottoNumbersGuide(manualLottoQuantity > EMPTY_LOTTO_QUANTITY)
        return lottoStore
    }

    private fun storeLottos(
        lottoStore: LottoStore,
        manualLottoQuantity: Int,
    ): LottoWallet {
        val lottoWallet = LottoWallet()
        val purchaseLottos =
            lottoStore.buy(
                List(manualLottoQuantity) { inputView.readLottoNumbers() },
            )
        lottoWallet.addAll(purchaseLottos)

        outputView.printPurchaseLottoQuantity(manualLottoQuantity, lottoStore.autoLottoQuantity)
        outputView.printLotto(lottoWallet.lottos)
        return lottoWallet
    }

    private fun discriminateLottos(
        winningDiscriminator: WinningDiscriminator,
        lottoWallet: LottoWallet,
        purchaseAmount: Int,
    ) {
        val winningResult = winningDiscriminator.getResult(lottoWallet.lottos)
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
