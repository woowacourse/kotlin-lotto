package lotto.model

import lotto.contants.LottoRuleConstants.LOTTO_PICK_COUNT

class WinningLotto(
    private val winningNumbers: Set<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(winningNumbers.size == LOTTO_PICK_COUNT)
        require(!winningNumbers.contains(bonusNumber))
    }

    fun getResult(lottoTickets: List<LottoTicket>): LottoResult {
        val result = lottoTickets.map { getRank(it) }
        return LottoResult(result)
    }

    fun getRank(lottoTicket: LottoTicket): Rank {
        val countOfMatch: Int = getCountOfMatch(lottoTicket)
        val matchBonus: Boolean = getMatchBonus(lottoTicket)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun getCountOfMatch(lottoTicket: LottoTicket): Int = winningNumbers.intersect(lottoTicket.getNumbers()).size

    private fun getMatchBonus(lottoTicket: LottoTicket): Boolean = lottoTicket.getNumbers().contains(bonusNumber)
}
