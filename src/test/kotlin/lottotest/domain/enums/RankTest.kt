package lottotest.domain.enums

import lotto.domain.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {
    val notMatchBonus = false
    val matchedBonus = true

    @ParameterizedTest
    @CsvSource(
        "4, FOURTH",
        "3, FIFTH",
        "2, MISS",
        "1, MISS",
        "0, MISS",
    )
    fun `보너스 일치와 상관없이 일치하는 번호가 4,3개이면 각 각 등수는 FOURTH, FIFTH이고 2개 이하이면 등수는 MISS이다`(
        countOfMatch: Int,
        expectedRank: Rank,
    ) {
        // When
        val rankWithoutBonus = Rank.valueOf(countOfMatch, notMatchBonus)
        val rankWithBonus = Rank.valueOf(countOfMatch, matchedBonus)

        // Then
        assertThat(rankWithoutBonus).isEqualTo(expectedRank)
        assertThat(rankWithBonus).isEqualTo(expectedRank)
    }

    @ParameterizedTest
    @CsvSource(
        "5, true, SECOND",
        "5, false, THIRD",
    )
    fun `일치하는 번호가 5개이고 보너스가 일치하면 등수는 SECOND, 보너스가 일치하지 않으면 THIRD이다`(
        countOfMatch: Int,
        bonus: Boolean,
        expectedRank: Rank,
    ) {
        // When
        val rank = Rank.valueOf(countOfMatch, bonus)

        // Then
        assertThat(rank).isEqualTo(expectedRank)
        assertThat(rank).isEqualTo(expectedRank)
    }

    @Test
    fun `일치하는 번호가 6개이면 보너스볼 여부와 상관 없이 등수는 FIRST이다`() {
        // Given
        val countOfMatch = 6
        val expectedRank = Rank.FIRST

        // When
        val rankWithoutBonus = Rank.valueOf(countOfMatch, notMatchBonus)
        val rankWithBonus = Rank.valueOf(countOfMatch, matchedBonus)

        // Then
        assertThat(rankWithoutBonus).isEqualTo(expectedRank)
        assertThat(rankWithBonus).isEqualTo(expectedRank)
    }
}
