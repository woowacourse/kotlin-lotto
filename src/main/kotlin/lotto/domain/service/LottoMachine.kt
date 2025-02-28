package lotto.domain.service

import lotto.Constants
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket

class LottoMachine {
    fun generateAutoTicket(count: Int): List<LottoTicket> = List(count) { LottoTicket(generateAutoLotto()) }

    fun calculateTotalCount(purchaseAmount: Int) = purchaseAmount / Constants.LOTTO_AMOUNT

    private fun generateAutoLotto(): List<LottoNumber> =
        LOTTO_RANGE
            .shuffled()
            .take(Constants.LOTTO_PICK_COUNT)
            .sorted()
            .map { LottoNumber(it) }

    companion object {
        private val LOTTO_RANGE = (Constants.MINIMUM_NUMBER..Constants.MAXIMUM_NUMBER)
    }
}
