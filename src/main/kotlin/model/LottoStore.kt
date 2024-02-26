package lotto.model

import model.Lotto
import model.LottoNumber

class LottoStore {
    companion object {
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

        fun generateManualLottos(manualLottos: List<List<String>>): Lottos {
            val lottoBundle =
                manualLottos.map { Lotto(LottoNumbers(it.map { LottoNumber(it.toInt()) }.sortedBy { it.number })) }
            return Lottos(lottoBundle)
        }
    }
}
