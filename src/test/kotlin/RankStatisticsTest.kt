import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankStatisticsTest {
    @Test
    fun `등수를 받아서 업데이트 하면 해당 등수 개수를 1 증가시킨다`() {
        // given
        val rank = Rank.FIFTH
        val rankStatistics = RankStatistics(3000)

        // when
        rankStatistics.updateRankCount(rank)
        rankStatistics.updateRankCount(rank)
        rankStatistics.updateRankCount(rank)

        // then
        assertThat(rankStatistics.getRankCount(rank)).isEqualTo(3)
    }

    @Test
    fun `해당하는 등수 개수를 반환한다`() {
        // given
        val rank = Rank.FIFTH
        val rankStatistics = RankStatistics(3000)

        // when
        rankStatistics.updateRankCount(rank)
        val actual = rankStatistics.getRankCount(rank)

        // then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `전체 로또의 수익률을 계산한다`() {
        // given
        val rank = Rank.FIFTH
        val rankStatistics = RankStatistics(3000)

        // when
        rankStatistics.updateRankCount(rank)
        rankStatistics.updateRankCount(rank)
        rankStatistics.updateRankCount(rank)

        // then
        assertThat(rankStatistics.getProfitRate()).isEqualTo(5.0)
    }
}
