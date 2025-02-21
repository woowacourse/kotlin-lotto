package lotto.model

class LottoScanner(
    private val winningNumbers: LottoTicket,
) {
    fun getResult(lottoTickets: List<LottoTicket>): LottoResult {
        val result = lottoTickets.map { getRank(it) }
        return LottoResult(result)
    }

    fun getRank(lottoTicket: LottoTicket): Rank {
        val countOfMatch: Int = getCountOfMatch(lottoTicket)
        val matchBonus: Boolean = getMatchBonus(lottoTicket)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun getCountOfMatch(lottoTicket: LottoTicket): Int =
        winningNumbers.getNumbers().intersect(lottoTicket.getNumbers().toSet()).size

    private fun getMatchBonus(lottoTicket: LottoTicket): Boolean = lottoTicket.getNumbers().contains(winningNumbers.getBonusNumber())
}
