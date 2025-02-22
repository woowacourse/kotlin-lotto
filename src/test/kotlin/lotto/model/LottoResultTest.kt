package lotto.model

import lotto.domain.model.Rank
import lotto.domain.service.LottoResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `수익률을 계산한다`() {
        val ranks = listOf(Rank.FIFTH, Rank.MISS)
        val lottoResult = LottoResult(ranks)
        val profit = lottoResult.calculateProfit()
        assertEquals(profit, 2.5)
    }
}
