package lotto.model

import model.Lotto

object LottoStore {
    fun generateAutoLottos(
        numberOfAutoLottos: Int,
        lottoNumberGenerator: NumberGenerator,
    ): Lottos {
        val lottoBundle = mutableListOf<Lotto>()
        repeat(numberOfAutoLottos) {
            lottoBundle.add(Lotto(lottoNumberGenerator.generate()))
        }
        return Lottos(lottoBundle)
    }

    fun generateManualLottos(lottoBundle: List<Lotto>): Lottos {
        return Lottos(lottoBundle)
    }
}
