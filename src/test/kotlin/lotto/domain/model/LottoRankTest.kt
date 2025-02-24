package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoRankTest {
    @CsvSource(
        "0, false, MISS",
        "3, false, FIFTH",
        "5, false, THIRD",
        "5, true, SECOND",
        "6, false, FIRST",
    )
    @ParameterizedTest
    fun `당첨 등수를 계산할 수 있다`(
        matchCount: Int,
        matchBonus: Boolean,
        lottoRank: LottoRank,
    ) {
        assertThat(LottoRank.calculate(matchCount, matchBonus)).isEqualTo(lottoRank)
    }
}
