package lotto.model

class WinningStatistic(private val winningStatistic: Pair<LottoPrize, Int>) {
    private val lottoPrize = winningStatistic.first
    private val count = winningStatistic.second

    init {
        require(lottoPrize.ordinal in LOTTO_PRIZE_MIN_ORDINAL..LOTTO_PRIZE_MAX_ORDINAL)
        require(count >= DEFAULT_COUNT)
    }

    fun getWinningStatistic(): Pair<LottoPrize, Int> = winningStatistic

    override fun toString(): String {
        return "${COUNT_OF_MATCH.format(lottoPrize.getCountOfMatch())}${
            SECOND_PRIZE_BONUS_MATCHED.takeIf { lottoPrize == LottoPrize.SECOND } ?: NOT_SECOND_PRIZE
        }${WINNING_AMOUNT.format(lottoPrize.getWinningAmount())}${COUNT.format(count)}"
    }

    companion object {
        private const val LOTTO_PRIZE_MIN_ORDINAL = 0
        private const val LOTTO_PRIZE_MAX_ORDINAL = 5
        private const val DEFAULT_COUNT = 0
        private const val COUNT_OF_MATCH = "%d개 일치"
        private const val SECOND_PRIZE_BONUS_MATCHED = ", 보너스 볼 일치"
        private const val NOT_SECOND_PRIZE = " "
        private const val WINNING_AMOUNT = "(%d원)"
        private const val COUNT = "- %d개"
    }
}
