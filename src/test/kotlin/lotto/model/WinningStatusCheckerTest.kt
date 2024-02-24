package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinningStatusCheckerTest {
    private lateinit var winningStatusChecker: WinningStatusChecker

    @BeforeEach
    fun setting() {
        winningStatusChecker =
            WinningStatusChecker(
                listOf(
                    WinningRank.FIRST,
                    WinningRank.FIRST,
                    WinningRank.FIFTH,
                    WinningRank.FIFTH,
                    WinningRank.MISS,
                ),
            )
    }

    @Test
    fun `당첨 결과에 따른 총 수익금을 계산하여 반환한다`() {
        assertThat(winningStatusChecker.getEarningRate()).isEqualTo(800_002.0)
    }
}
