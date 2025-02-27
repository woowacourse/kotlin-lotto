package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCashier
import lotto.model.LottoMachine
import lotto.model.LottoMachine.Companion.EMPTY_LOTTO_QUANTITY
import lotto.model.LottoNumber
import lotto.model.LottoProfitCalculator
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
        outputView.printPurchaseAmountGuide()
        val purchaseAmount = inputView.readPurchaseAmount()

        outputView.printManualLottoQuantityGuide()
        val manualLottoQuantity = inputView.readManualLottoQuantity()

        outputView.printManualLottoNumbersGuide(manualLottoQuantity > EMPTY_LOTTO_QUANTITY)
        val manualLottos = List(manualLottoQuantity) { Lotto.from(inputView.readLottoNumbers()) }

        val autoLottoQuantity = getAutoLottoQuantity(purchaseAmount, manualLottoQuantity)
        val lottos = getPurchaseLottos(manualLottos, autoLottoQuantity)

        outputView.printPurchaseLottoQuantity(manualLottoQuantity, autoLottoQuantity)
        outputView.printLottos(lottos)

        outputView.printWinningNumbersGuide()
        val winningNumbers = inputView.readLottoNumbers()

        outputView.printBonusNumberGuide()
        val bonusNumber = inputView.readBonusNumber()

        val lottoWinningResult = getLottosWinningResult(lottos, winningNumbers, bonusNumber)

        outputView.printWinningResultTitle()
        outputView.printWinningLottoResult(lottoWinningResult)

        val profitRate = getProfitRate(lottoWinningResult, purchaseAmount)
        val profitStatus = ProfitStatus.from(profitRate)

        outputView.printProfitRate(profitRate, profitStatus)
    }

    private fun getAutoLottoQuantity(
        purchaseAmount: Int,
        manualLottoQuantity: Int,
    ): Int {
        val lottoCashier = LottoCashier(purchaseAmount, manualLottoQuantity)

        return lottoCashier.getPurchaseAutoQuantity()
    }

    private fun getPurchaseLottos(
        manualLottos: List<Lotto>,
        autoLottoQuantity: Int,
    ): List<Lotto> {
        val lottoMachine = LottoMachine()

        return lottoMachine.getTotalLottos(
            manualLottos = manualLottos,
            autoQuantity = autoLottoQuantity,
        )
    }

    private fun getLottosWinningResult(
        lottos: List<Lotto>,
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): Map<Rank, Int> {
        val winningLotto = Lotto.from(winningNumbers)
        val bonusLottoNumber = LottoNumber.from(bonusNumber)
        val winningDiscriminator = WinningDiscriminator(winningLotto, bonusLottoNumber)

        return winningDiscriminator.getResult(lottos)
    }

    private fun getProfitRate(
        lottoWinningResult: Map<Rank, Int>,
        purchaseAmount: Int,
    ): Float = LottoProfitCalculator().getProfitRate(lottoWinningResult, purchaseAmount)
}
