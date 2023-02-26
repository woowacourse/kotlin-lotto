package domain

class NumberRandomGenerator : RandomGenerator {
    override fun generate(): Set<Int> =
        lottoNumbers.shuffled().take(6).toSet()

    companion object {
        private val lottoNumbers = (LottoNumber.MINIMUM_LOTTO_RANGE..LottoNumber.MAXIMUM_LOTTO_RANGE).toList()
    }
}
