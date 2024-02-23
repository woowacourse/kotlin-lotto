package model

private const val RESULT_MESSAGE = "%d개 일치%s(%d원)- %d개"
private const val MATCH_BONUS_NUMBER = ", 보너스 볼 일치"
private const val MISS_BONUS_NUMBER = " "

class WinningStatistic(val result: Pair<Rank, Int>) {
    override fun toString(): String {
        val rank = result.first
        val count = result.second
        return RESULT_MESSAGE.format(
            rank.countOfMatch,
            MATCH_BONUS_NUMBER.takeIf { result.first.ordinal == 1 } ?: MISS_BONUS_NUMBER,
            rank.winningAmount,
            count,
        )
    }
}
