import domain.Lotto
import domain.LottoNumber
import domain.Lottos
import domain.Rank
import domain.WinningNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `사용자가 원하는 개수 만큼 로또가 생성되는지 확인`() {
        val lottos = Lottos(testLottos)
        assertThat(lottos.lottos.size).isEqualTo(4)
    }

    @Test
    fun `전체 당첨 결과 확인`() {
        // given
        // Lottos = 1,2,3,4,5,6 / 1,2,4,6,34,45 / 3,5,15,18,24,35 / 4,6,15,18,24,35
        // Lottos = 1,2,3,4,6,10
        // bonusNumber = 45
        val testLottos = Lottos(testLottos)
        val winningLotto = Lotto(1, 2, 3, 4, 6, 10)
        val bonusNumber = LottoNumber.from(45)
        val winningNumber = WinningNumber(winningLotto, bonusNumber)

        // When
        val result = testLottos.matchLottos(winningNumber).winningRankResult

        // Then
        assertThat(result[Rank.FIRST]).isEqualTo(0) // 1등 당첨 0개
        assertThat(result[Rank.SECOND]).isEqualTo(0) // 2등 당첨 0개
        assertThat(result[Rank.THIRD]).isEqualTo(1) // 3등 당첨 1개
        assertThat(result[Rank.FOURTH]).isEqualTo(1) // 4등 당첨 1개
        assertThat(result[Rank.FIFTH]).isEqualTo(0) // 5등 당첨 0개
    }

    companion object {
        private val testLottos = listOf(
            Lotto(1, 2, 3, 4, 5, 6),
            Lotto(1, 2, 4, 6, 34, 45),
            Lotto(3, 5, 15, 18, 24, 35),
            Lotto(4, 6, 15, 18, 24, 35)
        )
    }
}
