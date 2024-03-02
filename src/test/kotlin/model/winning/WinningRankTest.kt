package model.winning

import model.Quantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WinningRankTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "6:false:FIRST", "6:true:FIRST",
            "5:true:SECOND", "5:false:THIRD",
            "4:true:FOURTH", "4:false:FOURTH",
            "3:true:FIFTH", "3:false:FIFTH",
            "2:true:NONE", "2:false:NONE",
            "1:true:NONE", "1:false:NONE",
            "0:true:NONE", "0:false:NONE",
        ],
        delimiter = ':',
    )
    fun `일치하는 수와 보너스 번호 일치 여부로 당첨 순위를 구한다`(
        numbersMatchCount: Int,
        bonusNumberMatch: Boolean,
        expectedRank: String,
    ) {
        val actualWinningRank = WinningRank.of(Quantity(numbersMatchCount), bonusNumberMatch)
        val expectedWinningRank = WinningRank.valueOf(expectedRank)

        assertThat(actualWinningRank).isEqualTo(expectedWinningRank)
    }
}
