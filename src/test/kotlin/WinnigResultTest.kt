import domain.Money
import domain.Rank
import domain.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinnigResultTest {

    @Test
    fun `1등 한번 4등 한번 당첨되었을 때 당첨금이 20억 5만원인지 확인`() {
        // given
        val winningresult = WinningResult()
        winningresult.setWinnigResult(Rank.FIRST) // 1등 당첨
        winningresult.setWinnigResult(Rank.FOURTH) // 2등 당첨

        // when
        val totalMoney = winningresult.getWinningMoney()

        // then
        assertThat(totalMoney).isEqualTo(2000050000) // 20억 5만원 획득
    }

    @Test
    fun `구입금액이 10000원일 때 4등, 5등 한번 씩 당첨됐을 경우 수익률 확인`() {
        // given
        // 구입금액 : 10000원
        val money = 10000
        val winningResult = WinningResult()
        winningResult.setWinnigResult(Rank.FOURTH) // 4등 당첨
        winningResult.setWinnigResult(Rank.FIFTH) // 5등 당첨

        // when
        val yield = winningResult.calculateYield(Money(10000))

        // then
        assertThat(yield).isEqualTo(5.5) // 5.5 수익률 달성
    }
}
