package lotto.domain.service

import lotto.domain.model.LottoTicket
import lotto.domain.model.WinningLotto

class LottoScanner(
    private val lottoTickets: List<LottoTicket>,
    private val winningLotto: WinningLotto,
) {
    fun getResult(): LottoResult {
        val result = lottoTickets.map { winningLotto.getRank(it) }
        return LottoResult(result)
    }
}
