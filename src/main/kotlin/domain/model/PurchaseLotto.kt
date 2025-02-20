package domain.model

import util.joinToLineBreak

class PurchaseLotto(
    val values: List<Lotto>,
) {
    override fun toString(): String = this.values.map { it.numbers.sorted() }.joinToLineBreak()
}
