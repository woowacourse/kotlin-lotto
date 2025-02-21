package lotto.domain.service

import lotto.ERROR_PREFIX
import lotto.LOTTO_PRICE
import lotto.domain.model.Lotto

class LottosGenerator(private val generator: LottoNumbersGenerator = RandomLottoNumbersGenerator()) {
    fun generate(amount: Int): List<Lotto> {
        val count = amount / LOTTO_PRICE
        require(count > MIN_LOTTO_COUNT_SIZE) { INVALID_LOTTO_COUNT_MESSAGE }
        return List(count) { Lotto(generator.generate()) }
    }

    private companion object {
        const val INVALID_LOTTO_COUNT_MESSAGE = "${ERROR_PREFIX}로또는 한 장 이상 구매해야 합니다."
        const val MIN_LOTTO_COUNT_SIZE = 0
    }
}
