import lotto.model.LottoPrize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoPrizeTest {
    @Test
    fun `당첨금 getMatchNumber Enum Class`() {
        assertThat(LottoPrize.FIFTH.matchNumbers).isEqualTo(3)
        assertThat(LottoPrize.FOURTH.matchNumbers).isEqualTo(4)
        assertThat(LottoPrize.THIRD.matchNumbers).isEqualTo(5)
        assertThat(LottoPrize.SECOND.matchNumbers).isEqualTo(5)
        assertThat(LottoPrize.FIRST.matchNumbers).isEqualTo(6)
    }

    @Test
    fun `당첨금 getPrice Enum Class`() {
        assertThat(LottoPrize.FIFTH.price).isEqualTo(5_000)
        assertThat(LottoPrize.FOURTH.price).isEqualTo(50_000)
        assertThat(LottoPrize.THIRD.price).isEqualTo(1_500_000)
        assertThat(LottoPrize.SECOND.price).isEqualTo(30_000_000)
        assertThat(LottoPrize.FIRST.price).isEqualTo(2_000_000_000)
    }
}
