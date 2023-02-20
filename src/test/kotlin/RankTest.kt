import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `5개 번호가 일치하고, 보너스 번호를 포함하고 있다면 2등이다`() {
        // given
        val matchOfCount = 5
        val bonusMatch = true

        // when
        val actual = Rank.valueOf(matchOfCount, bonusMatch)

        // then
        val expected = Rank.SECOND
        assertEquals(expected, actual)
    }
}
