package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningResultTest {

    @Test
    fun `일치 등수의 개수를 기록한다`() {
        val winningResult = WinningResult(mutableListOf(1, 0, 0, 0, 0))
        assertThat(winningResult.countMatchRanks).isEqualTo(mutableListOf(1, 0, 0, 0, 0))
    }
}
