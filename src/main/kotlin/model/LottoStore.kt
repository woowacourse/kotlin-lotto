package lotto.model

import model.Lotto

class LottoStore(private val numberOfLottos: Int, private val lottoNumberGenerator: NumberGenerator) {
    fun generateLottos(): Lottos {
        val lottoBundle = mutableListOf<Lotto>()
        repeat(numberOfLottos) {
            lottoBundle.add(Lotto(lottoNumberGenerator.generate()))
        }
        return Lottos(lottoBundle)
    }
}
