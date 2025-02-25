package lotto.domain.model

data class LottoOrder(
    val count: Int,
    val numbers: List<List<Int>> = emptyList(),
)
