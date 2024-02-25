package model

class WinningStatistic(val result: Pair<LottoPrize, Int>) {
    override fun toString(): String {
        val lottoPrize = result.first
        val count = result.second
        return "${lottoPrize.countOfMatch}개 일치${
            ", 보너스 볼 일치"
                .takeIf { result.first.ordinal == 1 } ?: " "
        }(${lottoPrize.winningAmount}원)- ${count}개"
    }
}
