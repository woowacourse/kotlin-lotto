package lotto.domain

fun interface NumberGenerator {
    fun generate(): List<LottoNumber>
}
