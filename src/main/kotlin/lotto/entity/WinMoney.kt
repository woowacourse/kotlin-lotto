package lotto.entity

class WinMoney private constructor(value: Int) : Money(value) {
    companion object {
        fun from(winStatistics: WinStatistics): WinMoney =
            WinMoney(winStatistics.value.sumOf { it.winningMoney.value })
    }
}