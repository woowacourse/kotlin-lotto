package domain

class NumberRandomGenerator : RandomGenerator {
    override fun generate(): Set<Int> =
        (LottoNumber.MINIMUM_LOTTO_RANGE..LottoNumber.MAXIMUM_LOTTO_RANGE).toList().shuffled().take(6).toSet()
}
