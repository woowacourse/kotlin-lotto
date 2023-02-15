package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningResultTest {

    @Test
    fun `일치 등수의 개수를 기록한다`() {
        val winningResult = WinningResult(mutableListOf(1, 0, 0, 0, 0, 0))
        assertThat(winningResult.countMatchRanks).isEqualTo(mutableListOf(1, 0, 0, 0, 0, 0))
    }

    @Test
    fun `등수를 받아 카운트한다`() {
        val winningResult = WinningResult(mutableListOf(1, 0, 0, 0, 0, 0))
        winningResult.countRank(Rank.SECOND)
        assertThat(winningResult.countMatchRanks).isEqualTo(listOf(1, 1, 0, 0, 0, 0))
    }
}
