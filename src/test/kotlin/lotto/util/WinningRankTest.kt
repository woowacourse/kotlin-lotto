package lotto.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WinningRankTest {
    @ParameterizedTest
    @CsvSource("6,False,FIRST", "5,True,SECOND", "5,False,THIRD", "2,False,MISS")
    fun `당첨 번호 일치 개수에 따른 등수 반환`(matchCount: Int, matchBonus: Boolean, result: WinningRank) {
        assertThat(WinningRank.convert(matchCount, matchBonus)).isEqualTo(result)
    }
}
