package domain

class RandomNumberGenerator : NumberGenerator {
    override fun generateSixNumber(start: Int, end: Int): Set<LottoNumber> =
        (start..end).toList().shuffled().take(6).map { number -> LottoNumber(number) }.toSet()
}
