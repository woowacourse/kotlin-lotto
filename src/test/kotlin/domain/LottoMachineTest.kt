package domain

import lotto.model.LottoMachine
import lotto.model.Margin
import lotto.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `구입 금액에 해당하는 로또 티켓을 반환하는지`() {
        val amount = 10_000L
        val actual = 10

        val result = LottoMachine(Money(amount)).countTicket()

        assertThat(result).isEqualTo(actual)
    }

    @Test
    fun `구입 금액 대비 수익 금액의 비율을 구한다`() {
        val actual = 0.357
        val winningPrize = Money(5_000)
        val money = Money(14_000)

        val expected = LottoMachine(money).calculateMargin(winningPrize)

        assertThat(expected).isEqualTo(Margin(actual))
    }
}
