package lotto.domain

import lotto.generator.LottoGenerator

class LottoMachine {
    fun buyLottos(lottoGenerator: LottoGenerator): List<Lotto> {
        return lottoGenerator.generateLotto()
    }

    companion object {
        const val LOTTO_PRICE: Int = 1_000
    }
}
