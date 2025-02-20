import domain.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `6개가 일치하면 1등 순위가 반환된다`() {
        val result = Rank.valueOf(6, false)
        assertThat(result).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개가 일치하고 보너스 번호가 일치하면 2등 순위가 반환된다`() {
        val result = Rank.valueOf(5, true)
        assertThat(result).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개가 일치하고 보너스 번호가 일치하지 않으면 3등 순위가 반환된다`() {
        val result = Rank.valueOf(5, false)
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `4개가 일치하고 보너스 번호가 일치하지 않으면 4등 순위가 반환된다`() {
        val result = Rank.valueOf(4, false)
        assertThat(result).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `4개가 일치하고 보너스 번호도 일치하면 4등 순위가 반환된다`() {
        val result = Rank.valueOf(4, true)
        assertThat(result).isEqualTo(Rank.FOURTH)
    }
}
