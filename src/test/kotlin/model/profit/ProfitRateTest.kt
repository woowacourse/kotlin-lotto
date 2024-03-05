package model.profit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitRateTest {
    @Test
    fun `수익률이 1 이면 이득이다`() {
        val profitRate = ProfitRate(1.0)
        val actualProfitStatus = profitRate.decideProfitStatus()
        assertThat(actualProfitStatus).isEqualTo(ProfitStatus.EVEN)
    }

    @Test
    fun `수익률이 1 미만이면 손해이다`() {
        val profitRate = ProfitRate(0.9)
        val actualProfitStatus = profitRate.decideProfitStatus()
        assertThat(actualProfitStatus).isEqualTo(ProfitStatus.LOSS)
    }

    @Test
    fun `수익률이 1 이면 초과이다`() {
        val profitRate = ProfitRate(1.1)
        val actualProfitStatus = profitRate.decideProfitStatus()
        assertThat(actualProfitStatus).isEqualTo(ProfitStatus.GAIN)
    }
}
