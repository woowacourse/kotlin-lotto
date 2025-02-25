package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoRankTest {
    @MethodSource("lottoRankTest")
    @ParameterizedTest
    fun `당첨 등수를 계산할 수 있다`(
        matchCount: Int,
        matchBonus: Boolean,
        lottoRank: LottoRank,
    ) {
        assertThat(LottoRank.calculate(matchCount, matchBonus)).isEqualTo(lottoRank)
    }

    companion object {
        @JvmStatic
        fun lottoRankTest(): List<Arguments> {
            return listOf(
                Arguments.arguments(0, false, LottoRank.MISS),
                Arguments.arguments(3, false, LottoRank.FIFTH),
                Arguments.arguments(4, false, LottoRank.FOURTH),
                Arguments.arguments(5, false, LottoRank.THIRD),
                Arguments.arguments(5, true, LottoRank.SECOND),
                Arguments.arguments(6, false, LottoRank.FIRST),
            )
        }
    }
}
