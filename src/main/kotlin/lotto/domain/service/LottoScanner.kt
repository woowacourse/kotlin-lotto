package lotto.domain.service

import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket
import lotto.domain.model.Rank

class LottoScanner(
    private val lottoTickets: List<LottoTicket>,
    private val winningNumbers: Set<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    fun getResult(): LottoResult {
        val result = lottoTickets.map { getRank(it) }
        return LottoResult(result)
    }

    fun getRank(lottoTicket: LottoTicket): Rank {
        val countOfMatch: Int = getCountOfMatch(lottoTicket)
        val matchBonus: Boolean = getMatchBonus(lottoTicket)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun getCountOfMatch(lottoTicket: LottoTicket): Int = winningNumbers.intersect(lottoTicket.getNumbers().toSet()).size

    private fun getMatchBonus(lottoTicket: LottoTicket): Boolean = lottoTicket.getNumbers().contains(bonusNumber)
}
