package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.Lottos
import lotto.model.ProfitStatus
import lotto.model.Rank
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val lottoMachine = generateLottoMachineByAmount()
        val lottos = displayLottos(lottoMachine)

        val lastWeekLottoWinningNumbers = inputView.readWinningNumbers()
        val lottoBonusNumber = inputView.readBonusNumber()

        val lottoWinningResult =
            displayLottosWinningResult(
                lottos,
                mapToLottoNumber(lastWeekLottoWinningNumbers),
                LottoNumber(lottoBonusNumber),
            )
        displayLottoWinningProfit(lottoMachine, lottoWinningResult)
    }

    private fun displayLottos(lottoMachine: LottoMachine): Lottos {
        val activeLottoQuantity = lottoMachine.getActiveLottoQuantity()
        outputView.printPurchaseLottoQuantity(lottoMachine.passiveLottoQuantity, activeLottoQuantity)
        val lottos = lottoMachine.getTotalLottos(activeLottoQuantity)

        lottos.getAllLottoNumbers().forEach { lottoNumbers ->
            outputView.printLottoNumbers(lottoNumbers)
        }

        return lottos
    }

    private fun List<Set<Int>>.mapToLotto(): List<Lotto> = this.map { Lotto(mapToLottoNumber(it)) }

    private fun mapToLottoNumber(lottoNumbers: Set<Int>): Set<LottoNumber> = lottoNumbers.map { LottoNumber(it) }.toSet()

    private fun generateLottoMachineByAmount(): LottoMachine {
        val purchaseAmount = inputView.readPurchaseAmount()
        val passiveLottoQuantity = inputView.readPassivePurchaseQuantity()
        val passiveLottoNumbers = inputView.readPassiveLottoNumbers(passiveLottoQuantity)
        return LottoMachine(purchaseAmount, passiveLottoQuantity, passiveLottoNumbers.mapToLotto())
    }

    private fun displayLottosWinningResult(
        lottos: Lottos,
        lastWeekWinningNumbers: Set<LottoNumber>,
        bonusNumber: LottoNumber,
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
        val profitRate = lottoMachine.getProfitRate(lottoWinningResult)
        val profitStatus = ProfitStatus.fromProfitStatus(profitRate)

        outputView.printProfitRate(profitRate, profitStatus.krDescription)
    }
}
