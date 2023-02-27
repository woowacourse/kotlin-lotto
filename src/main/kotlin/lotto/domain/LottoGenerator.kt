package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber

class LottoGenerator(private val randomNumberGenerator: LottoNumberGenerator) {

    fun generate(count: Int): List<Lotto> {
        return randomNumberGenerator.generate(count).map { lottoNumbers -> Lotto(lottoNumbers.toSet()) }
    }

    fun generateManual(lottoNumbers: List<Int>): Lotto {
        return Lotto(lottoNumbers.map { LottoNumber.from(it) }.toSet())
    }
}
