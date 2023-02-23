package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RankTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, 7])
    fun `랭크를 생성할 때 숫자가 매치된 개수가 0 이상 6 이하가 아니면 에러가 발생한다`(countOfMatch: Int) {
        assertThatIllegalArgumentException().isThrownBy { Rank.valueOf(countOfMatch, true) }
            .withMessage("등수가 나올 수 없는 경우입니다.\n잘못된 값: {매치 카운트=$countOfMatch, 매치 보너스=true}")
    }

    @Test
    fun `랭크를 생성할 때 6개의 숫자가 맞고 보너스가 매치되면 에러가 발생한다`() {
        val countOfMatch = 6

        assertThatIllegalArgumentException().isThrownBy { Rank.valueOf(countOfMatch, true) }
            .withMessage("등수가 나올 수 없는 경우입니다.\n잘못된 값: {매치 카운트=$countOfMatch, 매치 보너스=true}")
    }

    @ParameterizedTest
    @MethodSource("getCountOfMatchAndMatchBonus")
    fun `번호 매치 개수와 보너스 번호 매치 여부를 받으면 그에 맞는 랭크를 반환할 수 있다`(countOfMatch: Int, matchBonus: Boolean, rank: Rank) {
        val result = Rank.valueOf(countOfMatch, matchBonus)

        assertThat(result).isEqualTo(rank)
    }

    private fun getCountOfMatchAndMatchBonus() = listOf(
        Arguments.of(6, false, Rank.FIRST),
        Arguments.of(5, true, Rank.SECOND),
        Arguments.of(5, false, Rank.THIRD),
        Arguments.of(4, false, Rank.FOURTH),
        Arguments.of(3, false, Rank.FIFTH),
        Arguments.of(2, false, Rank.MISS),
    )
}
