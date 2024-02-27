package lotto.model

class FixedLottoNumbersGenerator(private val fixedNumbers: List<List<Int>>) : LottoNumbersGenerator {
    override fun generate(numberOfLottos: Int): List<LottoNumbers> = fixedNumbers.map { LottoNumbers(it.map(LottoNumber::of)) }
}
