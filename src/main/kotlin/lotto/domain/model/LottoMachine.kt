package lotto.domain.model

import lotto.domain.service.LottoNumbersGenerator
import lotto.domain.service.RandomLottoNumbersGenerator

class LottoMachine(
    val lottoPrice: Int = LOTTO_PRICE,
    private val generator: LottoNumbersGenerator = RandomLottoNumbersGenerator(),
) {
    fun generateLottoBundle(purchaseAmount: Int): LottoBundle {
        val count = purchaseAmount / lottoPrice
        return LottoBundle(List(count) { Lotto(generator.generate()) })
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
