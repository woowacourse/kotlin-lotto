package lotto.constant

import lotto.constant.Rank.FIRST
import lotto.constant.Rank.FOURTH
import lotto.constant.Rank.NOTHING
import lotto.constant.Rank.SECOND
import lotto.constant.Rank.THIRD
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class RankTest {
    @Test
    fun `당첨 로또 번호 개수가 5개이고 보너스 번호가 매치되면 2등이다`() {
        assertThat(Rank.convertToGrade(5, true)).isEqualTo(SECOND)
    }

    @Test
    fun `당첨 로또 번호 개수가 5개이고 보너스 번호가 매치되지 않으면 3등이다`() {
        assertThat(Rank.convertToGrade(5, false)).isEqualTo(THIRD)
    }

    @Test
    fun `당첨 로또 번호 개수가 6개면 보너스 번호에 상관없이 1등이다`() {
        assertThat(Rank.convertToGrade(6, false)).isEqualTo(FIRST)
    }

    @Test
    fun `당첨 로또 번호 개수가 4개면 보너스 번호에 상관없이 4등이다`() {
        assertThat(Rank.convertToGrade(4, true)).isEqualTo(FOURTH)
    }

    @MethodSource("provideRanks")
    @ParameterizedTest
    fun `등수를 당첨금액으로 번환할 수 있다`(rank: Rank) {
        assertThat(Rank.convertToPrizeMoney(rank)).isEqualTo(rank.prizeMoney)
    }

    companion object {
        @JvmStatic
        fun provideRanks() = listOf(
            Arguments.of(FIRST),
            Arguments.of(FOURTH),
            Arguments.of(NOTHING),
        )
    }
}
