package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCashier
import lotto.model.LottoMachine
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

        if (manualLottoQuantity > 0) outputView.printManualLottoNumbersGuide()
        val manualLottos = List(manualLottoQuantity) { Lotto.from(inputView.readLottoNumbers()) }

        val autoLottoQuantity = getAutoLottoQuantity(purchaseAmount, manualLottoQuantity)
        val lottos = getPurchaseLottos(manualLottos, autoLottoQuantity)

        outputView.printPurchaseLottoQuantity(manualLottoQuantity, autoLottoQuantity)
        lottos.forEach { lotto ->
            outputView.printLottoNumbers(lotto.numbers.map { it.number })
        }

        outputView.printWinningNumbersGuide()
        val winningNumbers = inputView.readLottoNumbers()

        outputView.printBonusNumberGuide()
        val bonusNumber = inputView.readBonusNumber()

        val lottoWinningResult = getLottosWinningResult(lottos, winningNumbers, bonusNumber)

        outputView.printWinningResultTitle()
        lottoWinningResult.forEach { (rank, count) ->
            if (rank == Rank.MISS) return@forEach

            outputView.printWinningResult(
                requiredMatch = rank.countOfMatch,
                profit = rank.winningMoney,
                matchBonus = rank == Rank.SECOND,
                countOfMatch = count,
            )
        }

        val profitRate = getProfitRate(lottoWinningResult, purchaseAmount)
        val profitStatus = ProfitStatus.from(profitRate)

        outputView.printProfitRate(profitRate, profitStatus)
    }

    private fun getAutoLottoQuantity(
        purchaseAmount: Int,
        manualLottoQuantity: Int,
    ): Int {
        val lottoCashier = LottoCashier(purchaseAmount, manualLottoQuantity)
        val autoLottoQuantity = lottoCashier.getPurchaseAutoQuantity()
        return autoLottoQuantity
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
