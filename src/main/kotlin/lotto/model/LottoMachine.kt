package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBER_SIZE
import lotto.model.LottoNumber.Companion.cachedLottoNumbers

class LottoMachine {
    fun getAutoLottos(quantity: Int): List<Lotto> = List(quantity) { Lotto(getAutoNumbers()) }

    private fun getAutoNumbers(): List<LottoNumber> =
        cachedLottoNumbers
            .shuffled()
            .take(LOTTO_NUMBER_SIZE)
            .sortedBy { number -> number.value }

    fun getManualLottos(ticket: List<List<Int>>): List<Lotto> =
        ticket.map { numbers ->
            Lotto.from(numbers)
        }

    companion object {
        const val EMPTY_LOTTO_QUANTITY = 0
    }
}
