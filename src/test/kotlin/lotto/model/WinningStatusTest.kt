package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinningStatusTest {
    private lateinit var winningStatus: WinningStatus

    @BeforeEach
    fun setting() {
        winningStatus =
            WinningStatus(
                mapOf(
                    WinningRank.FIRST to 2,
                    WinningRank.FIFTH to 2,
                    WinningRank.MISS to 1,
                ),
            )
    }

    @Test
    fun `당첨 결과에 따른 총 수익금을 계산하여 반환한다`() {
        assertThat(winningStatus.getEarningRate(5000)).isEqualTo(800_002.0)
    }
}
