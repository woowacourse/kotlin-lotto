package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProfitStatusTest {
    @Test
    fun `수익률이 1보다 초과하면 이득을 반환한다`() {
        // given
        val profitRate = 1.1f

        // when
        val actualProfitStatus = ProfitStatus.from(profitRate)

        // then
        assertEquals(ProfitStatus.PROFIT, actualProfitStatus)
    }

    @Test
    fun `수익률이 1보다 미만이면 손해를 반환한다`() {
        // given
        val profitRate = 0.9f

        // when
        val actualProfitStatus = ProfitStatus.from(profitRate)

        // then
        assertEquals(ProfitStatus.LOSS, actualProfitStatus)
    }

    @Test
    fun `수익률이 1이면 본전을 반환한다`() {
        // given
        val profitRate = 1f

        // when
        val actualProfitStatus = ProfitStatus.from(profitRate)

        // then
        assertEquals(ProfitStatus.BREAK_EVEN, actualProfitStatus)
    }
}
