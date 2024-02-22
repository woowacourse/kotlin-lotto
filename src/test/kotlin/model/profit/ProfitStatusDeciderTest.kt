package model.profit

import model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitStatusDeciderTest {
    private val purchaseAmount: Money = Money.wons(5000)

    @Test
    fun `수익률을 받아서 적합한 상태를 리턴한다(이득)`() {
        val winningAmount = Money.wons(10000)
        assertThat(ProfitStatusDecider.decide(purchaseAmount, winningAmount)).isEqualTo(ProfitStatus.GAIN)
    }

    @Test
    fun `수익률을 받아서 적합한 상태를 리턴한다(손해)`() {
        val winningAmount = Money.wons(300)
        assertThat(ProfitStatusDecider.decide(purchaseAmount, winningAmount)).isEqualTo(ProfitStatus.LOSS)
    }

    @Test
    fun `수익률을 받아서 적합한 상태를 리턴한다(본전)`() {
        val winningAmount = Money.wons(5000)
        assertThat(ProfitStatusDecider.decide(purchaseAmount, winningAmount)).isEqualTo(ProfitStatus.EVEN)
    }
}
