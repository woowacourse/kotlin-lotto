package lotto.domain

object LottoGenerator {
    private val randomNumberGenerator = RandomNumberGenerator()

    fun generateAuto(count: Int): List<Lotto> {
        val lottoNumbers = mutableListOf<Lotto>()
        repeat(count) { lottoNumbers.add(Lotto(randomNumberGenerator.generate())) }
        return lottoNumbers
    }

    fun generateManual(numbers: List<Int>): Lotto {
        return Lotto(numbers.map { LottoNumber.from(it) })
    }
}
