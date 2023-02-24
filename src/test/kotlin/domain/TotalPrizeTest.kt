package domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TotalPrizeTest {
    @Test
    fun `수익률을 계산하여 반환한다`() {
        // given
        val totalPrize: TotalPrize = TotalPrize(10000)
        val spendPayment: Payment = Payment(1000)

        // when
        val actual: Double = totalPrize.getEarningRate(spendPayment)

        // then
        Assertions.assertThat(actual).isEqualTo(10.0)
    }

    @Test
    fun `소수점 아래 둘째자리를 넘을시 반올림 해서 표현한다`() {
        // given
        val totalPrize: TotalPrize = TotalPrize(2000000000)
        val spendPayment: Payment = Payment(3000)

        // when
        val actual: Double = totalPrize.getEarningRate(spendPayment)

        // then
        Assertions.assertThat(actual).isEqualTo(666666.67)
    }
}
