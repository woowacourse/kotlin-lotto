package lotto.domain

object LottoGenerator {
    private val randomNumberGenerator = RandomNumberGenerator()

    fun generate(count: Int): List<Lotto> {
        val lottoNumbers = mutableListOf<Lotto>()
        repeat(count) { lottoNumbers.add(Lotto(randomNumberGenerator.generate())) }
        return lottoNumbers
    }
}
