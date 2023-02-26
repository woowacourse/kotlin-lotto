package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber

object LottoGenerator {
    private val randomNumberGenerator = RandomNumberGenerator()

    fun generate(count: Int): List<Lotto> {
        val lottoNumbers = mutableListOf<Lotto>()
        repeat(count) { lottoNumbers.add(Lotto(randomNumberGenerator.generate().toSet())) }
        return lottoNumbers
    }

    fun generateManual(lottoNumbers: List<Int>): Lotto {
        return Lotto(lottoNumbers.map { LottoNumber.from(it) }.toSet())
    }
}
