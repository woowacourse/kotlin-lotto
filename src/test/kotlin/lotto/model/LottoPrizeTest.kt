package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoPrizeTest {
    @Test
    fun `로또 당첨 등수 일치 확인`() {
        val countOfMatch = 5
        val bonusMatched = true

        val actual = LottoPrize.SECOND
        val expected = LottoPrize.getLottoPrize(countOfMatch, bonusMatched)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 당첨 등수 불일치 확인`() {
        val countOfMatch = 5
        val bonusMatched = false

        val actual = LottoPrize.SECOND
        val expected = LottoPrize.getLottoPrize(countOfMatch, bonusMatched)

        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun `Ordinal로 로또 등수 반환 일치 확인`() {
        val actual = LottoPrize.THIRD
        val expected = LottoPrize.getLottoPrizeByOrdinal(2)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ordinal로 로또 등수 반환 불일치 확인`() {
        val actual = LottoPrize.FIFTH
        val expected = LottoPrize.getLottoPrizeByOrdinal(2)

        assertThat(actual).isNotEqualTo(expected)
    }
}
