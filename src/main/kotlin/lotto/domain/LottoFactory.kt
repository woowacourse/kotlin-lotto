package lotto.domain

import lotto.service.LottoNumberGenerator

class LottoFactory(private val lottoNumberGenerator: LottoNumberGenerator) {
    fun generateLottoNumbers(): Lotto {
        val lottoNumbers = lottoNumberGenerator.generate()
        return Lotto(lottoNumbers)
    }

    fun generateLottos(amount: Int): List<Lotto> {
        return List(amount) { generateLottoNumbers() }
    }
}
