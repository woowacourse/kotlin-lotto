import domain.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `0개의 숫자가 맞고 보너스번호가 맞지 않으면 MISS다`() {
        // given
        val rank = Rank.valueOf(0, false)

        // then
        assertThat(rank).isEqualTo(Rank.MISS)
    }

    @Test
    fun `3개의 숫자가 맞으면 5등이다`() {
        // given
        val rank = Rank.valueOf(3, false)

        // then
        assertThat(rank).isEqualTo(Rank.FIFTH) // 5등
    }

    @Test
    fun `4개의 숫자가 맞으면 4등이다`() {
        // given
        val rank = Rank.valueOf(4, false)

        // then
        assertThat(rank).isEqualTo(Rank.FOURTH) // 4등
    }

    @Test
    fun `5개의 숫자가 맞고 보너스번호가 맞지 않으면 3등이다`() {
        // given
        val rank = Rank.valueOf(5, false)

        // then
        assertThat(rank).isEqualTo(Rank.THIRD) // 3등
    }

    @Test
    fun `5개의 숫자가 맞고 보너스번호가 맞으면 2등이다`() {
        // given
        val rank = Rank.valueOf(5, true)

        // then
        assertThat(rank).isEqualTo(Rank.SECOND) // 2등
    }

    @Test
    fun `6개의 숫자가 맞으면 1등이다`() {
        // given
        val rank = Rank.valueOf(6, false)

        // then
        assertThat(rank).isEqualTo(Rank.FIRST) // 1등
    }
}
