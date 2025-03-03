package lotto.domain.service

import lotto.Constants
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket
import lotto.domain.model.LottoTicketResult

class LottoMachine {
    fun generateManualTicket(
        input: () -> List<Int>,
        count: Int,
    ): List<LottoTicket> {
        val manualTickets = mutableListOf<LottoTicket>()

        repeat(count) {
            val numbers = input()
            val result = LottoTicket.create(numbers.map { LottoNumber(it) })
            when (result) {
                is LottoTicketResult.Success -> {
                    manualTickets.add(result.ticket)
                }

                is LottoTicketResult.InvalidCount -> throw IllegalArgumentException(ERROR_LOTTO_INVALID_COUNT)
                is LottoTicketResult.DuplicateNumbers -> throw IllegalArgumentException(ERROR_LOTTO_DUPLICATE)
            }
        }

        return manualTickets
    }

    fun generateAutoTicket(count: Int): List<LottoTicket> = List(count) { LottoTicket(generateAutoLotto()) }

    fun calculateTotalCount(purchaseAmount: Int) = purchaseAmount / Constants.LOTTO_AMOUNT

    private fun generateAutoLotto(): List<LottoNumber> =
        LOTTO_RANGE
            .shuffled()
            .take(Constants.LOTTO_PICK_COUNT)
            .sorted()
            .map { LottoNumber(it) }

    companion object {
        private const val ERROR_LOTTO_INVALID_COUNT = "로또 번호는 6개여야 합니다."
        private const val ERROR_LOTTO_DUPLICATE = "로또 번호는 서로 중복되면 안 됩니다."
        private val LOTTO_RANGE = (Constants.MINIMUM_NUMBER..Constants.MAXIMUM_NUMBER)
    }
}
