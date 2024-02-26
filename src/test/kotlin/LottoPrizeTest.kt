import lotto.model.LottoPrize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoPrizeTest {
    @Test
    fun `당첨금 getMatchNumber Enum Class`() {
        assertThat(LottoPrize.FIFTH.getMatchNumbers()).isEqualTo(3)
        assertThat(LottoPrize.FOURTH.getMatchNumbers()).isEqualTo(4)
        assertThat(LottoPrize.THIRD.getMatchNumbers()).isEqualTo(5)
        assertThat(LottoPrize.SECOND.getMatchNumbers()).isEqualTo(5)
        assertThat(LottoPrize.FIRST.getMatchNumbers()).isEqualTo(6)
    }

    @Test
    fun `당첨금 getPrice Enum Class`() {
        assertThat(LottoPrize.FIFTH.getPrice()).isEqualTo(5_000)
        assertThat(LottoPrize.FOURTH.getPrice()).isEqualTo(50_000)
        assertThat(LottoPrize.THIRD.getPrice()).isEqualTo(1_500_000)
        assertThat(LottoPrize.SECOND.getPrice()).isEqualTo(30_000_000)
        assertThat(LottoPrize.FIRST.getPrice()).isEqualTo(2_000_000_000)
    }
}
