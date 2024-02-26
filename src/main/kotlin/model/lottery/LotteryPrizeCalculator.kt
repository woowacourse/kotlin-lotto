package model.lottery

import model.Money
import model.WinningResult

class LotteryPrizeCalculator {
    fun calculate(winningResult: WinningResult): Money =
        winningResult.result.entries.sumOf {
            it.key.winningPrize * it.value.count
        }

    private inline fun <T> Iterable<T>.sumOf(selector: (T) -> Money): Money {
        var sum: Money = Money.ZERO
        for (element in this) {
            sum += selector(element)
        }
        return sum
    }
}
