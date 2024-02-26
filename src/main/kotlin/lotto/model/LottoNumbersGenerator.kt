package lotto.model

interface LottoNumbersGenerator {
    fun generate(count: Int): List<List<LottoNumber>>
}
