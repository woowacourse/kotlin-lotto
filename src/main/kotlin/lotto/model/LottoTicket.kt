package lotto.model

class LottoTicket private constructor(
    val lottoNumberSet: Set<LottoNumber?>,
) {
    init {
        require(lottoNumberSet.size == SIZE)
    }

    infix fun intersect(other: LottoTicket) = other.lottoNumberSet intersect this.lottoNumberSet

    operator fun contains(lottoNumber: LottoNumber) = lottoNumber in lottoNumberSet

    companion object {
        const val SIZE = 6

        fun from(lottoNumbers: List<LottoNumber?>): LottoTicket = LottoTicket(lottoNumbers.toSet())

        fun fromListToResult(lottoNumbers: List<LottoNumber?>): LottoTicketResult {
            val lottoNumberSet = lottoNumbers.toSet()
            return when {
                lottoNumberSet.size != SIZE -> LottoTicketResult.IsInvalidSizeOrDuplicated()
                null in lottoNumberSet -> LottoTicketResult.ContainsNullLottoNumberFailure()
                else -> LottoTicketResult.Success(LottoTicket(lottoNumberSet))
            }
        }
    }
}

sealed class LottoTicketResult {
    data class Success(val lottoTicket: LottoTicket) : LottoTicketResult()

    class ContainsNullLottoNumberFailure : LottoTicketResult()

    class IsInvalidSizeOrDuplicated : LottoTicketResult()
}
