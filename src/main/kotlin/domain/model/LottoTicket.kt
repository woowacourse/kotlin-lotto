package domain.model

class LottoTicket(
    val values: List<Lotto>,
) {
    override fun toString(): String = this.values.map { it.numbers.sorted() }.joinToString("\n")
}
