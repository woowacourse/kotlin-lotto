package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningResultTest {
    val winningResult = WinningResult.from(
        listOf<Rank>(Rank.NOTHING, Rank.SECOND, Rank.NOTHING),
    )

    @Test
    fun `2등에 1번 당첨된경우 최종 당첨금액은 30000000다`() {
        assertThat(
            winningResult.sumTotalPrizeMoney(),
        ).isEqualTo(
            30000000,
        )
    }
}
