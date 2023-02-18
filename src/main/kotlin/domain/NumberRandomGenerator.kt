package domain

class NumberRandomGenerator : RandomGenerator {
    override fun generate(): Set<LottoNumber> =
        (1..45).toList().shuffled().take(6).map { number -> LottoNumber(number) }.toSet()
}
