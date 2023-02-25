package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankStatisticsTest {

    @Test
    fun `해당하는 등수 개수를 반환한다`() {
        // given
        val ranks = listOf(Rank.FIFTH)
        val rank = Rank.FIFTH
        val rankStatistics = RankStatistics(ranks)

        // when
        val actual = rankStatistics.getRankCount(rank)

        // then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `전체 로또의 수익률을 계산한다`() {
        // given
        val ranks = listOf(Rank.FIFTH)
        val rankStatistics = RankStatistics(ranks)

        // when

        // then
        assertThat(rankStatistics.getProfitRate()).isEqualTo(5.0)
    }
}
