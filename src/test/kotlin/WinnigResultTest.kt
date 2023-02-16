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
}
