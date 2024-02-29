package model

import model.profit.ProfitRate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `Long 보다 작은 단위의 돈을 생성한다`() {
        val money = Money.from(9223372036854775800)
        assertThat(money).isEqualTo(Money.wons(9223372036854775800))
    }

    @Test
    fun `돈 객체 간의 뺄셈 연산을 수행한다`() {
        val money1 = Money.wons(1_500)
        val money2 = Money.wons(1_000)
        assertThat(money1 - money2).isEqualTo(Money.wons(500))
    }

    @Test
    fun `돈 객체 간의 덧셈 연산을 수행한다`() {
        val money1 = Money.wons(1_500_000_000_000)
        val money2 = Money.wons(1_000_000_000_000)
        assertThat(money1 + money2).isEqualTo(Money.wons(2_500_000_000_000))
    }

    @Test
    fun `돈 객체 간의 곱셈 연산을 수행한다`() {
        val money1 = Money.wons(1_500)
        val quantity = Quantity(4)
        assertThat(money1 * quantity).isEqualTo(Money.wons(6_000))
    }

    @Test
    fun `돈 객체 간의 나눗셈 연산을 수행한다`() {
        val money1 = Money.wons(1_500)
        val money2 = Money.wons(1_000)
        assertThat(money1 / money2).isEqualTo(1.5)
    }

    @Test
    fun `돈 객체 간의 나머지 연산을 수행한다`() {
        val money1 = Money.wons(1_500)
        val money2 = Money.wons(1_000)
        assertThat(money1 % money2).isEqualTo(Money.wons(500))
    }

    @Test
    fun `돈 객체 간의 대소를 비교한다`() {
        val money1 = Money.wons(1_500)
        val money2 = Money.wons(1_000)
        assertTrue(money1 > money2)
    }

    @Test
    fun `구매 금액과 총 당첨 금액의 수익률을 계산한다`() {
        // given
        val purchaseAmount = Money.wons(5_000)
        val totalWinningPrize = Money.wons(30_000_000)

        // when
        val result = purchaseAmount.calculateProfitRate(totalWinningPrize = totalWinningPrize)

        // then
        assertThat(result).isEqualTo(ProfitRate(6000.0))
    }
}
