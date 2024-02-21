package lotto.model

import model.Lotto

class LottoStore(private val numberOfLottos: Int, private val lottoNumberGenerator: NumberGenerator) {
    var lottos = Lottos()
        private set

    init {
        generateLottos()
    }

    private fun generateLottos() {
        repeat(numberOfLottos) {
            val lotto = Lotto(lottoNumberGenerator.generate())
            lottos.add(lotto)
        }
    }
}
