package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBER_SIZE
import lotto.model.LottoNumber.Companion.cachedLottoNumbers

class AutoLottoMachine : LottoMachine {
    override fun generate(
        manualTicket: List<List<Int>>,
        quantity: Int,
    ): List<Lotto> = List(quantity) { Lotto(getAutoNumbers()) }

    private fun getAutoNumbers(): List<LottoNumber> =
        cachedLottoNumbers
            .shuffled()
            .take(LOTTO_NUMBER_SIZE)
            .sortedBy { number -> number.value }
}
