import domain.Money
import domain.Rank
import domain.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinnigResultTest {

    @Test
    fun `1등 한번 4등 한번 당첨되었을 때 당첨금이 20억 5만원인지 확인`() {
        val winningresult = WinningResult()
        winningresult.setWinnigResult(Rank.FIRST)
        winningresult.setWinnigResult(Rank.FOURTH)
        assertThat(winningresult.getWinningMoney()).isEqualTo(2000050000)
    }

    @Test
    fun `구입금액이 10000원일 때 4등, 5등 한번 씩 당첨됐을 경우 수익률 확인`() {
        val winningResult = WinningResult()
        winningResult.setWinnigResult(Rank.FOURTH)
        winningResult.setWinnigResult(Rank.FIFTH)
        assertThat(winningResult.calculateYield(Money(10000))).isEqualTo(5.5)
    }
}
