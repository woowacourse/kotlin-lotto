package lotto.model

class FixedLottoNumbersGenerator(private val fixedNumbers: List<List<Int>>) : LottoNumbersGenerator {
    override fun generate(numberOfLottos: Int): List<List<LottoNumber>> = fixedNumbers.map { it.map(LottoNumber::of) }
}
