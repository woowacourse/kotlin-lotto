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
        val hasBonusNumber: Boolean = hasBonusNumber(lottoTicket)
        return Rank.valueOf(countOfMatch, hasBonusNumber)
    }

    private fun getCountOfMatch(lottoTicket: LottoTicket): Int = lottoTicket.countMatchingNumbers(winningNumbers)

    private fun hasBonusNumber(lottoTicket: LottoTicket): Boolean = lottoTicket.hasBonusNumber(bonusNumber)
}
