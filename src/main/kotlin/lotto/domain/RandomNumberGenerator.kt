package lotto.domain

class RandomNumberGenerator : LottoNumberGenerator {
    override fun generate(): List<LottoNumber> {
        return (1..45).toList().shuffled().slice(0..5).sorted().map { number -> LottoNumber(number) }
    }
}
