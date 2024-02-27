package model.profit

import model.Money
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ProfitStatusTest {
    private val purchaseAmount: Money = Money.wons(5000)

    @Test
    fun `수익률을 받아서 적합한 상태를 리턴한다(이득)`() {
        val winningAmount = Money.wons(10000)
        Assertions.assertThat(ProfitStatus.decide(purchaseAmount, winningAmount)).isEqualTo(ProfitStatus.GAIN)
    }

    @Test
    fun `수익률을 받아서 적합한 상태를 리턴한다(손해)`() {
        val winningAmount = Money.wons(3000)
        Assertions.assertThat(ProfitStatus.decide(purchaseAmount, winningAmount)).isEqualTo(ProfitStatus.LOSS)
    }

    @Test
    fun `수익률을 받아서 적합한 상태를 리턴한다(본전)`() {
        val winningAmount = Money.wons(5000)
        Assertions.assertThat(ProfitStatus.decide(purchaseAmount, winningAmount)).isEqualTo(ProfitStatus.EVEN)
    }
}
