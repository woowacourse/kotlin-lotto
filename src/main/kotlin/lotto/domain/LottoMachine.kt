package lotto.domain

import lotto.generator.LottoGenerator

class LottoMachine(private val randomGenerator: LottoGenerator = LottoGenerator()) {
    fun buyLottos(lottoCount: Int): List<Lotto> {
        return List(lottoCount) { Lotto(randomGenerator.getRandomLottoNumbers(NUMBER_OF_LOTTO_NUMBER)) }
    }

    companion object {
        private const val NUMBER_OF_LOTTO_NUMBER: Int = 6
    }
}
