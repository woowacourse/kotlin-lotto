import lotto.model.LottoPrize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoPrizeTest {
    @Test
    fun `당첨금 Enum Class`() {
        assertThat(
            LottoPrize.FIFTH.apply {
                Triple(
                    getMatchNumbers(),
                    getPrice(),
                    LottoPrize.getLottoMessage(this)
                )
            }
        ).isEqualTo(Triple(3, 5_000, "3개 일치 (5,000원) -"))
        assertThat(
            LottoPrize.FOURTH.apply {
                Triple(
                    getMatchNumbers(),
                    getPrice(),
                    LottoPrize.getLottoMessage(this)
                )
            }
        ).isEqualTo(Triple(4, 50_000, "4개 일치 (50,000원) -"))
        assertThat(
            LottoPrize.THIRD.apply {
                Triple(
                    getMatchNumbers(),
                    getPrice(),
                    LottoPrize.getLottoMessage(this)
                )
            }
        ).isEqualTo(Triple(5, 1_500_000, "5개 일치 (1,500,000원) -"))
        assertThat(
            LottoPrize.SECOND.apply {
                Triple(
                    getMatchNumbers(),
                    getPrice(),
                    LottoPrize.getLottoMessage(this)
                )
            }
        ).isEqualTo(Triple(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원) -"))
        assertThat(
            LottoPrize.FIRST.apply {
                Triple(
                    getMatchNumbers(),
                    getPrice(),
                    LottoPrize.getLottoMessage(this)
                )
            }
        ).isEqualTo(Triple(6, 2_000_000_000, "6개 일치 (2,000,000,000원) -"))
    }
}
