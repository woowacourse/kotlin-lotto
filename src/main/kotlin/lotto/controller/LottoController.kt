package lotto.controller

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
        val lottoQuantity = getLottoQuantity(lottoMachine)

        val lottos = displayLottos(lottoMachine, lottoQuantity)

        val lastWeekLottoWinningNumbers = getLastWeekLottoWinningNumbers().validateWinningNumbers()
        val lottoBonusNumber = getLottoBonusNumbers().validateIsNumber()

        val lottoWinningResult =
            displayLottosWinningResult(lottos, lastWeekLottoWinningNumbers, LottoNumber(lottoBonusNumber))
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

    private fun getLastWeekLottoWinningNumbers(): String = inputView.readWinningNumbers()

    private fun mapToWinningNumbers(rawWinningNumbers: String): Set<String> = rawWinningNumbers.split(", ").toSet()

    private fun mapToNumber(rawNumber: String) = rawNumber.toIntOrNull()

    private fun String.validateWinningNumbers(): Set<LottoNumber> {
        try {
            val rawWinningNumbers = mapToWinningNumbers(this)
            return rawWinningNumbers.map { number -> LottoNumber(number.validateIsNumber()) }.toSet()
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(e.message)
        }
    }

    private fun String.validateIsNumber(): Int = mapToNumber(this) ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해주세요.")

    private fun getLottoBonusNumbers(): String = inputView.readBonusNumber()

    private fun generateLottoMachineByAmount(): LottoMachine {
        val rawPurchaseAmount = inputView.readPurchaseAmount()
        val purchaseAmount = rawPurchaseAmount.validateIsNumber()
        return LottoMachine(purchaseAmount)
    }

    private fun getLottoQuantity(lottoMachine: LottoMachine): Int {
        val lottoQuantity = lottoMachine.getLottoQuantity()
        outputView.printPurchaseLottoQuantity(lottoQuantity)
        return lottoQuantity
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
