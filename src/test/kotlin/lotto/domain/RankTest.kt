package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {
    @ParameterizedTest
    @CsvSource(
        "6,false,FIRST, 로또 번호가 6개 일치하면 1등이다",
        "5,true,SECOND, 로또 번호가 5개 일치하고 보너스번호가 일치하면 2등이다",
        "5,false,THIRD, 로또 번호가 5개 일치하고 보너스번호가 일치하지 않으면 3등이다",
        "4,true,FOURTH, 로또 번호가 4개 일치하면 4등이다",
        "3,false,FIFTH, 로또 번호가 3개 일치하면 5등이다",
        "2,true,MISS, 로또 번호가 2개 일치하면 등수가 없다"
    )
    fun `일치하는 개수와 보너스번호 매치 여부를 알려주면 등수를 알려준다`(
        countOfMatch: Int,
        matchBonus: Boolean,
        expected: Rank,
        testName: String
    ) {
        println(testName)

        assertThat(Rank.valueOf(countOfMatch, matchBonus)).isEqualTo(expected)
    }

    @Test
    fun `해당 등수 당첨자의 총 상금을 계산한다`() {
        assertThat(Rank.calculatePrize("FIRST", 2)).isEqualTo(4000000000)
    }
}
