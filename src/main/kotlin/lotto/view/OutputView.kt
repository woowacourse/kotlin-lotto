package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoAmount
import lotto.domain.LottoResult
import lotto.domain.Profit
import lotto.domain.Rank

object OutputView {
    fun printManualLottoHeader() {
        println(MANUAL_LOTTO_NUMBER_MESSAGE)
    }

    fun printLottoAmount(
        amount: LottoAmount,
        manualAmount: LottoAmount,
    ) {
        val manualCount = manualAmount.toInt()
        val autoCount = amount.toInt() - manualCount

        println(LOTTO_PURCHASE_MESSAGE.format(manualCount, autoCount))
    }

    fun printLottos(lottos: List<Lotto>) {
        lottos.forEach { lotto ->
            val formattedNumbers = lotto.lottoNums.joinToString(DELIMETER_MESSAGE) { it.lottoNumber.toString() }
            println(LOTTO_NUMBERS_FORMAT.format(formattedNumbers))
        }
    }

    fun printResult(lottoResult: LottoResult) {
        printHeader()
        printStatistics(lottoResult)
    }

    private fun printHeader() {
        println(RESULT_HEADER_MESSAGE)
        println(RESULT_DIVIDER)
    }

    private fun printStatistics(lottoResult: LottoResult) {
        Rank.entries.reversed()
            .filter { it != Rank.MISS }
            .forEach { rank ->
                println(formatWinningMessage(rank, lottoResult))
            }
    }

    private fun formatWinningMessage(
        rank: Rank,
        lottoResult: LottoResult,
    ): String {
        val bonusText = if (rank == Rank.SECOND) BONUS_BALL_MATCH_MESSAGE else ""
        val count = lottoResult.getWinningStatistics().getOrDefault(rank, 0)

        return WINNING_STATISTICS_FORMAT.format(rank.countOfMatch, bonusText, rank.winningMoney, count)
    }

    fun printProfit(profitRate: Double) {
        val profit = Profit.profitOf(profitRate)
        val profitMessage = getProfitMessage(profit)
        println(PROFIT_MESSAGE.format(profitRate, profitMessage))
    }

    fun getProfitMessage(profit: Profit): String {
        return ProfitMessage.getMessage(profit)
    }

    private const val MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
    private const val LOTTO_PURCHASE_MESSAGE = "수동으로 %d개, 자동으로 %d개를 구매했습니다."
    private const val DELIMETER_MESSAGE = ", "
    private const val LOTTO_NUMBERS_FORMAT = "[%s]"
    private const val RESULT_HEADER_MESSAGE = "\n당첨 통계"
    private const val RESULT_DIVIDER = "---------"
    private const val WINNING_STATISTICS_FORMAT = "%d개 일치%s (%d원) - %d개"
    private const val BONUS_BALL_MATCH_MESSAGE = ", 보너스 볼 일치"
    private const val PROFIT_MESSAGE = "총 수익률은 %.2f입니다. (%s)"
}
