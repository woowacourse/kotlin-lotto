package lotto.model

class LottoScanner(
    private val winningNumbers: LottoTicket,
) {
    fun getResult(lottos: List<LottoTicket>): LottoResult {
        val result = lottos.map { getRank(it) }
        return LottoResult(result)
    }

    fun getRank(lotto: LottoTicket): Rank {
        val countOfMatch: Int = getCountOfMatch(lotto)
        val matchBonus: Boolean = getMatchBonus(lotto)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun getCountOfMatch(lotto: LottoTicket): Int = winningNumbers.getNumbers().intersect(lotto.getNumbers().toSet()).size

    private fun getMatchBonus(lotto: LottoTicket): Boolean = lotto.getNumbers().contains(winningNumbers.getBonusNumber())
}
