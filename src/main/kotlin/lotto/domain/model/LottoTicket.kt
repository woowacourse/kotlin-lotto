package lotto.domain.model

import lotto.Constants

sealed class LottoTicketResult {
    data class Success(
        val ticket: LottoTicket,
    ) : LottoTicketResult()

    data object InvalidCount : LottoTicketResult()

    data object DuplicateNumbers : LottoTicketResult()
}

class LottoTicket(
    numbers: List<LottoNumber>,
) {
    val numbers: Set<LottoNumber> = numbers.toSet()

    fun countMatchingNumbers(winningNumbers: Set<LottoNumber>): Int = numbers.intersect(winningNumbers).size

    fun hasNumber(number: LottoNumber): Boolean = numbers.contains(number)

    companion object {
        fun create(numbers: List<LottoNumber>): LottoTicketResult {
            if (numbers.size != Constants.LOTTO_PICK_COUNT) return LottoTicketResult.InvalidCount
            if (numbers.toSet().size != Constants.LOTTO_PICK_COUNT) return LottoTicketResult.DuplicateNumbers
            return LottoTicketResult.Success(LottoTicket(numbers))
        }
    }
}
