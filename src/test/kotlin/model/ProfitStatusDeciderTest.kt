package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ProfitStatusDeciderTest {
    private val purchaseAmount: Money = Money(BigDecimal(5000))

    @Test
    fun `수익률을 받아서 적합한 상태를 리턴한다(이득)`() {
        val winningAmount: Money = Money(BigDecimal(10000))
        assertThat(ProfitStatusDecider.decide(purchaseAmount, winningAmount)).isEqualTo(ProfitStatus.GAIN)
    }

    @Test
    fun `수익률을 받아서 적합한 상태를 리턴한다(손해)`() {
        val winningAmount: Money = Money(BigDecimal(300))
        assertThat(ProfitStatusDecider.decide(purchaseAmount, winningAmount)).isEqualTo(ProfitStatus.LOSS)
    }

    @Test
    fun `수익률을 받아서 적합한 상태를 리턴한다(본전)`() {
        val winningAmount: Money = Money(BigDecimal(5000))
        assertThat(ProfitStatusDecider.decide(purchaseAmount, winningAmount)).isEqualTo(ProfitStatus.EVEN)
    }
}
