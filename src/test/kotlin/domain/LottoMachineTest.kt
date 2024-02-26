package domain

import domain.model.Margin
import domain.model.Money
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
        val winningPrize = Money(5000)

        val expected = LottoMachine(Money(14000)).calculateMargin(winningPrize)

        assertThat(expected).isEqualTo(Margin(actual))
    }
}
