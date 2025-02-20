package lotto.controller

import lotto.model.LottoMachine
import lotto.model.Lottos
import lotto.model.Rank
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val lottoMachine = generateLottoMachineByAmount()
        val lottoQuantity = getLottoQuantity(lottoMachine)

        val lottos = displayLottos(lottoMachine, lottoQuantity)

        val lastWeekLottoWinningNumbers = getLastWeekLottoWinningNumbers()
        val lottoBonusNumber = getLottoBonusNumbers()

        val lottoWinningResult = displayLottosWinningResult(lottos, lastWeekLottoWinningNumbers, lottoBonusNumber)
        displayLottoWinningProfit(lottoMachine, lottoWinningResult)
    }

    private fun displayLottos(
        lottoMachine: LottoMachine,
        lottoQuantity: Int,
    ): Lottos {
        val lottos = lottoMachine.getLottos(lottoQuantity)

        lottos.getAllLottoNumbers().forEach { lottoNumbers ->
            outputView.printLottoNumbers(lottoNumbers)
        }

        return lottos
    }

    private fun getLastWeekLottoWinningNumbers(): List<Int> {
        outputView.printLastWeekWinningNumbersGuide()
        return inputView.readWinningNumbers()
    }

    private fun getLottoBonusNumbers(): Int {
        outputView.printBonusNumberGuide()
        return inputView.readBonusNumber()
    }

    private fun generateLottoMachineByAmount(): LottoMachine {
        outputView.printPurchaseAmountGuide()
        val purchaseAmount = inputView.readPurchaseAmount()
        return LottoMachine(purchaseAmount)
    }

    private fun getLottoQuantity(lottoMachine: LottoMachine): Int {
        val lottoQuantity = lottoMachine.getLottoQuantity()
        outputView.printPurchaseLottoQuantity(lottoQuantity)
        return lottoQuantity
    }

    private fun displayLottosWinningResult(
        lottos: Lottos,
        lastWeekWinningNumbers: List<Int>,
        bonusNumber: Int,
    ): Map<Rank, Int> {
        val lottoWinningResult = lottos.countLottoByRank(lastWeekWinningNumbers, bonusNumber)
        outputView.printWinningResultTitle()
        lottoWinningResult.forEach { (rank, count) ->
            displayLottoWinningResult(rank, count)
        }
        return lottoWinningResult
    }

    private fun displayLottoWinningResult(
        rank: Rank,
        count: Int,
    ) {
        if (rank == Rank.MISS) return

        outputView.printWinningResult(
            requiredMatch = rank.countOfMatch,
            profit = rank.winningMoney,
            matchBonus = rank.matchBonus,
            countOfMatch = count,
        )
    }

    private fun displayLottoWinningProfit(
        lottoMachine: LottoMachine,
        lottoWinningResult: Map<Rank, Int>,
    ) {
        outputView.printProfitRate(lottoMachine.getProfitRate(lottoWinningResult))
    }
}
