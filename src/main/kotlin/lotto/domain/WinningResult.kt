package lotto.domain

class WinningResult(
    val countMatchRanks: MutableList<Int> = MutableList(6) { 0 }
)
