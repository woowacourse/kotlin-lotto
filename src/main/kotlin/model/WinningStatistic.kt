package model

class WinningStatistic(val result: Pair<Rank, Int>) {
    override fun toString(): String {
        val rank = result.first
        val count = result.second
        return "${rank.countOfMatch}개 일치${", 보너스 볼 일치".takeIf { result.first.ordinal == 1 } ?: " "}(${rank.winningAmount}원)- ${count}개"
    }
}
