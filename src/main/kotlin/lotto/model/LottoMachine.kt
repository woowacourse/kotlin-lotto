package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBER_SIZE
import lotto.model.LottoNumber.Companion.ALL_LOTTO_NUMBERS

class LottoMachine {
    fun getAutoLottos(quantity: Int): List<Lotto> = List(quantity) { Lotto.from(getAutoNumbers()) }

    private fun getAutoNumbers(): List<Int> =
        ALL_LOTTO_NUMBERS
            .shuffled()
            .take(LOTTO_NUMBER_SIZE)
            .sorted()

    companion object {
        const val EMPTY_LOTTO_QUANTITY = 0
    }
}
