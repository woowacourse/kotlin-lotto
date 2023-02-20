package lotto.domain

class RandomNumberGenerator : LottoNumberGenerator {
    override fun generate(): List<LottoNumber> {
        return (LottoNumber.MINIMUM_LOTTO_NUMBER..LottoNumber.MAXIMUM_LOTTO_NUMBER).shuffled()
            .slice(0 until Lotto.LOTTO_SIZE)
            .sorted().map { number -> LottoNumber(number) }
    }
}
