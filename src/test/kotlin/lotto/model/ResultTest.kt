package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun `등수별 당첨 횟수 증가 기능을 검증한다`() {
        val result =
            Result(
                mutableMapOf(
                    Rank.THIRD to 1,
                    Rank.SECOND to 1,
                ),
            )
        result.incrementRankCount(Rank.THIRD)
        Assertions.assertThat(result.getWinningCountByRank(Rank.THIRD)).isEqualTo(2)
    }

    @Test
    fun `총 지출이 2000원일때, 2등과 3등이 된다면 수익률은 15750 이다`() {
        val result =
            Result(
                mutableMapOf(
                    Rank.THIRD to 1,
                    Rank.SECOND to 1,
                ),
            )
        Assertions.assertThat(result.calculateProfitRate(2000)).isEqualTo(15750.0)
    }
}
