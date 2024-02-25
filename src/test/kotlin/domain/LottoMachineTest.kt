package domain

import domain.model.LottoDrawingResult
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
    fun `로또 결과에 대한 총 상금을 구한다`() {
        val actual = 1_500_000L
        val rank = mapOf(Rank.FIRST to 0, Rank.SECOND to 0, Rank.THIRD to 1, Rank.FOURTH to 0, Rank.FIFTH to 0)
        val expected = LottoMachine(Money(1000)).calculateTotalPrize(LottoDrawingResult(rank))

        assertThat(expected).isEqualTo(Money(actual))
    }

    @Test
    fun `Int형 범위를 넘어가는 총 상금이어도 정상 작동한다`() {
        val actual = 4_000_000_000
        val rank = mapOf(Rank.FIRST to 2, Rank.SECOND to 0, Rank.THIRD to 0, Rank.FOURTH to 0, Rank.FIFTH to 0)
        val expected = LottoMachine(Money(10_000)).calculateTotalPrize(LottoDrawingResult(rank))

        assertThat(expected).isEqualTo(Money(actual))
    }

    @Test
    fun `구입 금액 대비 수익 금액의 비율을 구한다`() {
        val actual = 0.357
        val winningPrize = Money(5000)

        val expected = LottoMachine(Money(14000)).calculateMargin(winningPrize)

        assertThat(expected).isEqualTo(Margin(actual))
    }
}
