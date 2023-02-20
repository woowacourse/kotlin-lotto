import domain.Money
import domain.Rank
import domain.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinnigResultTest {

    @Test
    fun `구입금액이 10000원일 때 4등, 5등 한번 씩 당첨됐을 경우 수익률 확인`() {
        val winningResult = WinningResult(mapOf(Rank.FOURTH to 1, Rank.FIFTH to 1))
        assertThat(winningResult.calculateYield(Money(10000))).isEqualTo(5.5)
    }

    @Test
    fun `구입금액이 100000원일 때 2등, 3등, 5등 한번 씩 당첨됐을 경우 수익률 확인`() {
        val winningResult = WinningResult(mapOf(Rank.SECOND to 1, Rank.THIRD to 1, Rank.FIFTH to 1))
        assertThat(winningResult.calculateYield(Money(100000))).isEqualTo(315.05)
    }

    @Test
    fun `구입금액이 100000원일 때 1등 한 번 당첨됐을 경우 수익률 확인`() {
        val winningResult = WinningResult(mapOf(Rank.FIRST to 1))
        assertThat(winningResult.calculateYield(Money(100000))).isEqualTo(20000.0)
    }
}
