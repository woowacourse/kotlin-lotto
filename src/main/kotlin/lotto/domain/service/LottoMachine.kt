package lotto.domain.service

import lotto.Constants
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket

class LottoMachine {
    fun calculateAutoCount(
        totalCount: Int,
        manualCount: Int,
    ): Int = totalCount - manualCount

    fun purchase(count: Int): List<LottoTicket> = List(count) { LottoTicket(generateLotto()) }

    fun calculateTotalCount(purchaseAmount: Int) = purchaseAmount / Constants.LOTTO_AMOUNT

    private fun generateLotto(): List<LottoNumber> =
        LOTTO_RANGE
            .shuffled()
            .take(Constants.LOTTO_PICK_COUNT)
            .sorted()
            .map { LottoNumber(it) }

    companion object {
        private val LOTTO_RANGE = (Constants.MINIMUM_NUMBER..Constants.MAXIMUM_NUMBER)
    }
}
