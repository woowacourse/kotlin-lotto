package lotto.constant

import lotto.domain.Rank
import lotto.domain.Rank.FIRST
import lotto.domain.Rank.FOURTH
import lotto.domain.Rank.NOTHING
import lotto.domain.Rank.SECOND
import lotto.domain.Rank.THIRD
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments

class RankTest {
    @Test
    fun `당첨 로또 번호 개수가 5개이고 보너스 번호가 매치되면 2등이다`() {
        assertThat(Rank.convertToRank(5, true)).isEqualTo(SECOND)
    }

    @Test
    fun `당첨 로또 번호 개수가 5개이고 보너스 번호가 매치되지 않으면 3등이다`() {
        assertThat(Rank.convertToRank(5, false)).isEqualTo(THIRD)
    }

    @Test
    fun `당첨 로또 번호 개수가 6개면 보너스 번호에 상관없이 1등이다`() {
        assertThat(Rank.convertToRank(6, false)).isEqualTo(FIRST)
    }

    @Test
    fun `당첨 로또 번호 개수가 4개면 보너스 번호에 상관없이 4등이다`() {
        assertThat(Rank.convertToRank(4, true)).isEqualTo(FOURTH)
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
