package lotto.domain.service

import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottoResultTest {
    private fun lotto(vararg numbers: Int): LottoTicket = LottoTicket(numbers.map { LottoNumber(it) })

    @Test
    fun `당첨 개수에 맞는 등수를 반환한다`() {
        val lottoTicket = listOf(lotto(1, 2, 3, 4, 5, 6), lotto(1, 2, 3, 4, 5, 7))
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), LottoNumber(8))
        val lottoResult = LottoResult.calculateResult(lottoTicket, winningLotto)
        assertEquals(lottoResult.ranks, mapOf(Rank.FIRST to 1, Rank.THIRD to 1))
    }
}
