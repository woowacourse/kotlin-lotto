package domain.model

class PurchaseLotto(val values: List<Lotto>) {
    override fun toString(): String {
        return this.values.map { it.numbers }.joinToString("\n")
    }
}
