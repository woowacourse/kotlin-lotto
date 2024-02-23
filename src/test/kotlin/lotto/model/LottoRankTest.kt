package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoRankTest {
    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `6개의 당첨번호가 일치하며 4등이다`(matchBonus: Boolean) {
        val lottoRank = LottoRank.valueOf(6, matchBonus)
        assertThat(lottoRank).isEqualTo(LottoRank.FIRST)
    }

    @Test
    fun `5개의 당첨번호와 보너스 번호가 일치하면 2등이다`() {
        val lottoRank = LottoRank.valueOf(5, true)
        assertThat(lottoRank).isEqualTo(LottoRank.SECOND)
    }

    @Test
    fun `5개의 당첨번호가 일치하고 보너스 번호가 일치하지 않으면 3등이다`() {
        val lottoRank = LottoRank.valueOf(5, false)
        assertThat(lottoRank).isEqualTo(LottoRank.THIRD)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `4개의 당첨번호가 일치하며 4등이다`(matchBonus: Boolean) {
        val lottoRank = LottoRank.valueOf(4, matchBonus)
        assertThat(lottoRank).isEqualTo(LottoRank.FOURTH)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `3개의 당첨번호가 일치하며 5등이다`(matchBonus: Boolean) {
        val lottoRank = LottoRank.valueOf(3, matchBonus)
        assertThat(lottoRank).isEqualTo(LottoRank.FIFTH)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2])
    fun `3개 미만의 당첨번호가 일치하면 꽝(MISS)이다`(countOfMatch: Int) {
        val lottoRank = LottoRank.valueOf(countOfMatch, true)
        assertThat(lottoRank).isEqualTo(LottoRank.MISS)
    }
}
