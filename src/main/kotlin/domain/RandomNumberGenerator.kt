package domain

object RandomNumberGenerator {
    fun generate(): List<Int> =
        (LottoNumber.MINIMUM_NUMBER..LottoNumber.MAXIMUM_NUMBER).shuffled().take(Lotto.NUMBER_SIZE).sorted()
}
