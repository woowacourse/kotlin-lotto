package lotto.domain

class TestGenerateStrategy(private val lottoTickets: MutableList<LottoTicket>) : GenerateStrategy {
    override fun generate(): LottoTicket {
        return lottoTickets.removeAt(0)
    }
}
