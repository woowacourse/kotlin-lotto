package lotto.model

import model.Lotto

class LottoStore(private val numberOfLottos: Int, private val lottoNumberGenerator: NumberGenerator) {
    var lottos: MutableList<Lotto> = mutableListOf()
        private set

    init {
        generateLottos()
    }

    fun generateLottos() {
        repeat(numberOfLottos) {
            val lotto = Lotto(lottoNumberGenerator.generate())
            lottos.add(lotto)
        }
    }
}
