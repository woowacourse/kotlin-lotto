package model

class WinningStatistic(private val result: Pair<LottoPrize, Int>) {
    fun getResult(): Pair<LottoPrize, Int> = result

    override fun toString(): String {
        val lottoPrize = result.first
        val count = result.second
        return "${lottoPrize.getCountOfMatch()}개 일치${
            ", 보너스 볼 일치"
                .takeIf { result.first.ordinal == 1 } ?: " "
        }(${lottoPrize.getWinningAmount()}원)- ${count}개"
    }
}
