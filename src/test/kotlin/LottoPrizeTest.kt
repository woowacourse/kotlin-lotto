import lotto.model.LottoPrize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoPrizeTest {
    @Test
    fun `당첨금 Enum Class`() {
        assertThat(
            Pair(
                LottoPrize.FIFTH.getMatchNumbers(),
                LottoPrize.FIFTH.getPrice()
            )
        ).isEqualTo(Pair(3, 5_000))
        assertThat(
            Pair(
                LottoPrize.FOURTH.getMatchNumbers(),
                LottoPrize.FOURTH.getPrice()
            )
        ).isEqualTo(Pair(4, 50_000))
        assertThat(
            Pair(
                LottoPrize.THIRD.getMatchNumbers(),
                LottoPrize.THIRD.getPrice()
            )
        ).isEqualTo(Pair(5, 1_500_000))
        assertThat(
            Pair(
                LottoPrize.SECOND.getMatchNumbers(),
                LottoPrize.SECOND.getPrice()
            )
        ).isEqualTo(Pair(5, 30_000_000))
        assertThat(
            Pair(
                LottoPrize.FIRST.getMatchNumbers(),
                LottoPrize.FIRST.getPrice()
            )
        ).isEqualTo(Pair(6, 2_000_000_000))
    }
}
