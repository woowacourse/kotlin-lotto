package lotto.model

class LottoMachine(
    count: Int,
) {
    val lottoTickets: List<Lotto> = generateLottoTicket(count)

    companion object {
        private fun generateLottoTicket(count: Int): List<Lotto> {
            val lottoTickets = mutableListOf<Lotto>()
            repeat(count) {
                lottoTickets.add(Lotto())
            }
            return lottoTickets
        }
    }
}
