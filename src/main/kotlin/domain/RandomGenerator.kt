package domain

interface RandomGenerator {
    fun generate(): Set<LottoNumber>
}
