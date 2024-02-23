package lotto.model

class FixedLottoNumbersGenerator(private val fixedNumbers: List<Int>) : LottoNumbersGenerator {
    override fun generate(): List<LottoNumber> = fixedNumbers.take(Lotto.LOTTO_SIZE).sorted().map { LottoNumber.of(it) }
}
