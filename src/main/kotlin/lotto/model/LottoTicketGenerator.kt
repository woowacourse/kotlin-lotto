package lotto.model

interface LottoTicketGenerator {
    val type: LottoIssueType
    fun generateLottoTicket() : LottoTicket
}