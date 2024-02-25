import lotto.model.LottoPrize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoPrizeTest {
    @Test
    fun `당첨금 Enum Class`() {
        assertThat(
            LottoPrize.FIFTH.apply {
                Pair(
                    getMatchNumbers(),
                    getPrice()
                )
            }
        ).isEqualTo(Pair(3, 5_000))
        assertThat(
            LottoPrize.FOURTH.apply {
                Pair(
                    getMatchNumbers(),
                    getPrice()
                )
            }
        ).isEqualTo(Pair(4, 50_000))
        assertThat(
            LottoPrize.THIRD.apply {
                Pair(
                    getMatchNumbers(),
                    getPrice()
                )
            }
        ).isEqualTo(Pair(5, 1_500_000))
        assertThat(
            LottoPrize.SECOND.apply {
                Pair(
                    getMatchNumbers(),
                    getPrice()
                )
            }
        ).isEqualTo(Pair(5, 30_000_000))
        assertThat(
            LottoPrize.FIRST.apply {
                Pair(
                    getMatchNumbers(),
                    getPrice()
                )
            }
        ).isEqualTo(Pair(6, 2_000_000_000))
    }
}
