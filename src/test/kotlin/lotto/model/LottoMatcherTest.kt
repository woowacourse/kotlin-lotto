package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMatcherTest {
    @Test
    fun `당첨 번호와 일치하는 번호가 6개인 경우 1등이 된다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val winningNumber = listOf(1, 2, 3, 4, 5, 6)
        val publishedLotto = parseLottoNumber(numbers)
        val winningLotto = parseLottoNumber(winningNumber)
        val bonusNumber = LottoNumber(10)
        val lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
        val rank = lottoMatcher.calculateRank(publishedLotto)
        assertThat(rank).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 5개이고, 보너스 볼이 일치하는 경우 2등이 된다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val winningNumber = listOf(1, 2, 3, 4, 5, 7)
        val publishedLotto = parseLottoNumber(numbers)
        val winningLotto = parseLottoNumber(winningNumber)
        val bonusNumber = LottoNumber(6)
        val lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
        val rank = lottoMatcher.calculateRank(publishedLotto)
        assertThat(rank).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 5개인 경우 3등이 된다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val winningNumber = listOf(1, 2, 3, 4, 5, 7)
        val publishedLotto = parseLottoNumber(numbers)
        val winningLotto = parseLottoNumber(winningNumber)
        val bonusNumber = LottoNumber(10)
        val lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
        val rank = lottoMatcher.calculateRank(publishedLotto)
        assertThat(rank).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 4개인 경우 4등이 된다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val winningNumber = listOf(1, 2, 3, 4, 7, 8)
        val publishedLotto = parseLottoNumber(numbers)
        val winningLotto = parseLottoNumber(winningNumber)
        val bonusNumber = LottoNumber(10)
        val lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
        val rank = lottoMatcher.calculateRank(publishedLotto)
        assertThat(rank).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 3개인 경우 5등이 된다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val winningNumber = listOf(1, 2, 3, 7, 8, 9)
        val publishedLotto = parseLottoNumber(numbers)
        val winningLotto = parseLottoNumber(winningNumber)
        val bonusNumber = LottoNumber(10)
        val lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
        val rank = lottoMatcher.calculateRank(publishedLotto)
        assertThat(rank).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 3개 미만인 경우 탈락된다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val winningNumber = listOf(1, 2, 7, 8, 9, 10)
        val publishedLotto = parseLottoNumber(numbers)
        val winningLotto = parseLottoNumber(winningNumber)
        val bonusNumber = LottoNumber(11)
        val lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
        val rank = lottoMatcher.calculateRank(publishedLotto)
        assertThat(rank).isEqualTo(Rank.MISS)
    }

    private fun parseLottoNumber(numbers: List<Int>): Lotto {
        val lottoNumbers = numbers.map { number -> LottoNumber(number) }
        return Lotto(lottoNumbers)
    }
}
