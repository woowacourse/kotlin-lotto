package lotto.model

class ManualLottoTicketGenerator(
    private val numbers: List<Int>
) : LottoTicketGenerator {
    override val type: LottoIssueType = LottoIssueType.MANUAL

    override fun generateLottoTicket(): LottoTicket = LottoTicket(LottoIssueType.MANUAL, numbers.map(::LottoNumber))
}