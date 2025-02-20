package domain.model

class PurchaseLotto(
    val values: List<Lotto>,
) {
    override fun toString(): String = this.values.map { it.numbers.sorted() }.joinToString("\n")
}
