package lotto.view

import lotto.model.LottoBundle
import lotto.model.LottoResult
import lotto.model.Rank

object OutputView {
    private const val LOTTO_SIZE_MESSAGE = "%d개를 구매했습니다."
    private const val WINNING_STATISTICS_MESSAGE = "당첨 통계\n---------\n"
    private const val TOTAL_RETURN_MESSAGE = "총 수익률은 %.2f입니다."
    private const val WINNING_BONUS_MESSAGE = "%d개 일치, 보너스 볼 일치(%d)- %d개"
    private const val WINNING_MESSAGE = "%d개 일치 (%d)- %d개"

    private const val EMPTY_COUNT = 0

    fun printLottoBundle(lottoBundle: LottoBundle) {
        println(LOTTO_SIZE_MESSAGE.format(lottoBundle.lottos.size))
        println(lottoBundle)
        println()
    }

    fun printResult(lottoResult: LottoResult) {
        println(WINNING_STATISTICS_MESSAGE)
        Rank.entries.reversed().forEach { rank ->
            val count = lottoResult.static[rank] ?: EMPTY_COUNT
            when (rank) {
                Rank.MISS -> Unit
                Rank.SECOND -> println(WINNING_BONUS_MESSAGE.format(rank.countOfMatch, rank.winningMoney, count))
                else -> println(WINNING_MESSAGE.format(rank.countOfMatch, rank.winningMoney, count))
            }
        }
        println(TOTAL_RETURN_MESSAGE.format(lottoResult.getProfitRate()))
    }
}
