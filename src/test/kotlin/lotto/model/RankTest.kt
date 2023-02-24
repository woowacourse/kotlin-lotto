package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {

    @CsvSource(value = ["6,false,FIRST", "5,true,SECOND", "5,false,THIRD", "4,true,FOURTH", "3,false,FIFTH", "2,true,MISS"])
    @ParameterizedTest
    fun `일치하는 번호 개수와 보너스 번호 일치 여부로 등수 검사`(countOfMatch: Int, isMatchBonus: Boolean, except: Rank) {
        val actual = Rank.determineAll(countOfMatch, isMatchBonus)

        assertThat(except).isEqualTo(actual)
    }
}
