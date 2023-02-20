package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BankTest {

    @Test
    fun `수익률을 계산하여 반환한다`() {
        // given
        val totalPrize: Long = 10000
        val spendMoney: Money = Money(1000)

        // when
        val actual: Double = Bank.getEarningRate(totalPrize, spendMoney)

        // then
        assertThat(actual).isEqualTo(10.0)
    }
}
