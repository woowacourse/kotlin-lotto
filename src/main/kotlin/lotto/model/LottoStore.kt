package lotto.model

class LottoStore {
    fun getTickets(
        input: String,
        lottoGenerator: LottoGenerator,
    ): List<Lotto> {
        val ticketCount = LottoTicketCounter(input).count()
        return lottoGenerator.generate(ticketCount)
    }
}
