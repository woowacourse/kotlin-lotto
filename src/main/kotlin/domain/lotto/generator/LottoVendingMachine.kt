package domain.lotto.generator

import domain.lotto.Lotto
import domain.lotto.LottoBundle

object LottoVendingMachine {

    fun getLottoBundle(lottoCount: Int, lottoGenerator: LottoGenerator = RandomLottoGenerator()): LottoBundle {
        val lottos = mutableListOf<Lotto>()
        repeat(lottoCount) {
            lottos.add(lottoGenerator.generate())
        }
        return LottoBundle(lottos)
    }
}
