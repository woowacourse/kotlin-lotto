package model.winning

import model.Money
import model.Quantity

data class WinningResult(val result: Map<WinningRank, Quantity>) {
    fun calculate(): Money =
        result.entries.sumOf {
            it.key.winningPrize * it.value
        }
}

private inline fun <T> Iterable<T>.sumOf(selector: (T) -> Money): Money {
    var sum: Money = Money.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}
