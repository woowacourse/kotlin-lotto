package lotto.domain.model

import lotto.domain.service.LottoNumbersGenerator
import lotto.domain.service.RandomLottoNumbersGenerator

class LottoMachine(
    private val lottoPrice: Int = 1000,
    private val generator: LottoNumbersGenerator = RandomLottoNumbersGenerator(),
) {
    fun generateLottoBundle(purchaseAmount: Int): LottoBundle {
        val count = purchaseAmount / lottoPrice
        return LottoBundle(List(count) { Lotto(generator.generate()) })
    }
}
