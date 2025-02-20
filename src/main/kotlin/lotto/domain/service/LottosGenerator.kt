package lotto.domain.service

import lotto.LOTTO_PRICE
import lotto.domain.model.Lotto

class LottosGenerator(private val generator: LottoNumbersGenerator = RandomLottoNumbersGenerator()) {
    fun generate(amount: Int): List<Lotto> {
        val count = amount / LOTTO_PRICE
        return List(count) { Lotto(generator.generate()) }
    }
}
