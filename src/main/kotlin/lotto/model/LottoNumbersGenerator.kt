package lotto.model

interface LottoNumbersGenerator {
    fun generate(numberOfLottos: Int): List<List<LottoNumber>>
}
