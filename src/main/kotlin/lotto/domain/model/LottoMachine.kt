package lotto.domain.model

import lotto.domain.service.LottoNumbersGenerator
import lotto.domain.service.RandomLottoNumbersGenerator

class LottoMachine(
    private val generator: LottoNumbersGenerator = RandomLottoNumbersGenerator(),
) {
    fun generateLottoBundle(purchaseLottoCount: Int): LottoBundle {
        return LottoBundle(List(purchaseLottoCount) { Lotto(generator.generate()) })
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
