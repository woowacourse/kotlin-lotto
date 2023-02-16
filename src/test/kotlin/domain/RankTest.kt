package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class RankTest {

    @ParameterizedTest
    @CsvSource(
        value = [
            "6 : false : FIRST", "5 : true : SECOND", "5 : false : THIRD", "4 : false : FOURTH", "3 : false : FIFTH", "2 : false : MISS",
        ],
        delimiter = ':',
    )
    fun `번호 매치 개수와 보너스 번호 매치 여부를 받으면 그에 맞는 랭크를 반환할 수 있다`(countOfMatch: Int, matchBonus: Boolean, rank: Rank) {
        val result = Rank.valueOf(countOfMatch, matchBonus)

        assertThat(result).isEqualTo(rank)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 7])
    fun `숫자가 매치된 개수가 0 이상 6 이하가 아니면 에러가 발생한다`(countOfMatch: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { Rank.valueOf(countOfMatch, true) }
            .withMessage("맞는 숫자의 개수는 0 이상 6 이하여야 합니다.\n잘못된 값: $countOfMatch")
    }

    @Test
    fun `6개의 숫자가 맞을 때 보너스가 매치되면 에러가 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Rank.valueOf(6, true) }
            .withMessage("숫자가 6개 맞으면 보너스 번호가 매치될 수 없습니다.")
    }
}
