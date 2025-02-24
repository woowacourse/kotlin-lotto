package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProfitStatusTest {
    @Test
    fun `수익률이 1보다 초과하면 이득을 반환한다`() {
        testProfitStatus(1.1f, ProfitStatus.PROFIT)
    }

    @Test
    fun `수익률이 1보다 미만이면 손해를 반환한다`() {
        testProfitStatus(0.9f, ProfitStatus.LOSS)
    }

    @Test
    fun `수익률이 1이면 본전을 반환한다`() {
        testProfitStatus(1f, ProfitStatus.BREAK_EVEN)
    }

    private fun testProfitStatus(
        profitRate: Float,
        expectedProfitStatus: ProfitStatus,
    ) {
        val actualProfitStatus = ProfitStatus.from(profitRate)
        assertEquals(expectedProfitStatus, actualProfitStatus)
    }
}
