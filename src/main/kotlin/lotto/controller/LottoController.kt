package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCashier
import lotto.model.LottoMachine
import lotto.model.LottoMachine.Companion.EMPTY_LOTTO_QUANTITY
import lotto.model.LottoNumber
import lotto.model.LottoProfitCalculator
import lotto.model.LottoWallet
import lotto.model.ProfitStatus
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
        val manualLottoQuantity = getManualLottoQuantity()
        val lottoCashier = LottoCashier(purchaseAmount, manualLottoQuantity)

        val lottoWallet = geterateLottoWallet(manualLottoQuantity)
        val autoLottoQuantity = lottoCashier.getPurchaseAutoLottoQuantity()
        val lottoMachine = LottoMachine()

        addAutoLottosToWallet(lottoMachine, lottoWallet, autoLottoQuantity)
        displayPurchasedLottos(lottoWallet, manualLottoQuantity, autoLottoQuantity)

        val winningLotto = getWinningNumbers()
        val bonusLottoNumber = getBonusNumber()
        val winningDiscriminator = WinningDiscriminator(winningLotto, bonusLottoNumber)
        val lottoWinningResult = winningDiscriminator.getResult(lottoWallet.get())

        displayWinningResults(lottoWinningResult)
        displayProfitResults(lottoWinningResult, purchaseAmount)
    }

    private fun getPurchaseAmount(): Int {
        outputView.printPurchaseAmountGuide()
        return inputView.readPurchaseAmount()
    }

    private fun getManualLottoQuantity(): Int {
        outputView.printManualLottoQuantityGuide()
        return inputView.readManualLottoQuantity()
    }

    private fun geterateLottoWallet(manualLottoQuantity: Int): LottoWallet {
        outputView.printManualLottoNumbersGuide(manualLottoQuantity > EMPTY_LOTTO_QUANTITY)
        val lottoWallet = LottoWallet()
        repeat(manualLottoQuantity) {
            lottoWallet.add(inputView.readLottoNumbers())
        }
        return lottoWallet
    }

    private fun addAutoLottosToWallet(
        lottoMachine: LottoMachine,
        lottoWallet: LottoWallet,
        autoLottoQuantity: Int,
    ) {
        val autoLottos = lottoMachine.getAutoLottos(autoLottoQuantity)
        lottoWallet.addAll(autoLottos)
    }

    private fun displayPurchasedLottos(
        lottoWallet: LottoWallet,
        manualLottoQuantity: Int,
        autoLottoQuantity: Int,
    ) {
        outputView.printPurchaseLottoQuantity(manualLottoQuantity, autoLottoQuantity)
        lottoWallet.get().forEach { lotto ->
            outputView.printLotto(lotto.getRawNumbers())
        }
    }

    private fun getWinningNumbers(): Lotto {
        outputView.printWinningNumbersGuide()
        val winningNumbers = inputView.readLottoNumbers()

        return Lotto.from(winningNumbers)
    }

    private fun getBonusNumber(): LottoNumber {
        outputView.printBonusNumberGuide()
        val bonusNumber = inputView.readBonusNumber()

        return LottoNumber.from(bonusNumber)
    }

    private fun displayWinningResults(lottoWinningResult: Map<Rank, Int>) {
        outputView.printWinningResultTitle()
        outputView.printWinningLottoResult(lottoWinningResult)
    }

    private fun displayProfitResults(
        lottoWinningResult: Map<Rank, Int>,
        purchaseAmount: Int,
    ) {
        val profitRate = LottoProfitCalculator().getProfitRate(lottoWinningResult, purchaseAmount)
        val profitStatus = ProfitStatus.from(profitRate)

        outputView.printProfitRate(profitRate, profitStatus)
    }
}
