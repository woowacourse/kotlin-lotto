package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBER_SIZE
import lotto.model.LottoNumber.Companion.LOTTO_NUMBER_MAX_RANGE
import lotto.model.LottoNumber.Companion.LOTTO_NUMBER_MIN_RANGE

class LottoMachine {
    fun getAutoLottos(quantity: Int): List<Lotto> = List(quantity) { Lotto.from(getAutoNumbers()) }

    private fun getAutoNumbers(): List<Int> =
        (LOTTO_NUMBER_MIN_RANGE..LOTTO_NUMBER_MAX_RANGE)
            .shuffled()
            .take(LOTTO_NUMBER_SIZE)
            .sorted()

    companion object {
        const val EMPTY_LOTTO_QUANTITY = 0
    }
}
