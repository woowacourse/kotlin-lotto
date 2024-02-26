package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningRankTest {
    @Test
    fun `일치하는 수와 보너스 번호 일치 여부로 당첨 순위를 구한다`() {
        val expectedWinningRank = WinningRank.FIFTH
        val actualWinningRank =
            WinningRank.of(
                numbersMatchCount = 3,
                bonusNumberMatch = true,
            )

        assertThat(actualWinningRank).isEqualTo(expectedWinningRank)
    }
}
