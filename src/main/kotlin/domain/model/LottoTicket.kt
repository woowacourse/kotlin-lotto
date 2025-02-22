package domain.model

import domain.model.Lotto.Companion.toValues

class LottoTicket(
    val values: List<Lotto>,
) {
    override fun toString(): String = this.values.map { it.lottoNumbers.toValues().sorted() }.joinToString("\n")
}
