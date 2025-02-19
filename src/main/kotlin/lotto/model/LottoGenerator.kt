package lotto.model

interface LottoGenerator {
    fun generate(count: Int): List<Lotto>
}
