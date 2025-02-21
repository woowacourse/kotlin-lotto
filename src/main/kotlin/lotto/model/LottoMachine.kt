package lotto.model

class LottoMachine(
    private val count: Int,
) {
    fun purchase(count: Int): List<LottoTicket> = List(count) { LottoTicket() }

    val lottoTickets: List<LottoTicket> = generateLottoTickets(count)

    companion object {
        private fun generateLottoTickets(count: Int): List<LottoTicket> {
            val lottoTickets = mutableListOf<LottoTicket>()
            repeat(count) {
                lottoTickets.add(LottoTicket())
            }
            return lottoTickets
        }
    }
}
