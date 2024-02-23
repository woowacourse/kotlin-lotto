package lotto.model

class LottoFixedNumberGenerator(private val fixedNumbers: List<Int>) : LottoNumberGenerator {
    override fun generate(): List<LottoNumber> = fixedNumbers.take(Lotto.LOTTO_SIZE).sorted().map { LottoNumber.of(it) }
}
