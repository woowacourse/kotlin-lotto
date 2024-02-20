import lotto.model.matchBonusNumber
import lotto.model.matchCount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGameTest {
    @Test
    fun `보너스 매치`() {
        assertThat(matchBonusNumber(setOf(1,2,3,4,5,6),5)).isTrue()
    }
    @Test
    fun `당첨 계산`() {
        assertThat(matchCount(setOf(1,2,3,4,5,6), setOf(1,2,3,4,5,6))).isEqualTo(6)
    }
}