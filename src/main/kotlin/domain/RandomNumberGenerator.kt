package domain

class RandomNumberGenerator : NumberGenerator {
    override fun generate(): List<Int> =
        (LottoNumber.MINIMUM_NUMBER..LottoNumber.MAXIMUM_NUMBER).shuffled().take(Lotto.NUMBER_SIZE).sorted()
}
