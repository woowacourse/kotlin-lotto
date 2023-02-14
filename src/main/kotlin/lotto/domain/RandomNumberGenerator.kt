package lotto.domain

class RandomNumberGenerator : LottoNumberGenerator {
    override fun generate(): List<Int> {
        return (1..45).toList().shuffled().slice(0..5)
    }
}