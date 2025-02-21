package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoMatcherTest {
    private lateinit var winningLotto: Lotto
    private lateinit var lottoMatcher: LottoMatcher

    @BeforeEach
    fun setup() {
        val bonusNumber = LottoNumber(7)
        winningLotto = parseLottoNumber(listOf(1, 2, 3, 4, 5, 6))
        lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
    }

    @Test
    fun `당첨 번호와 보너스 볼은 중복될 수 없다`() {
        val winningNumber = listOf(1, 2, 3, 4, 5, 6)
        val winningLotto = parseLottoNumber(winningNumber)
        val bonusNumber = LottoNumber(1)
        val exception = assertThrows<IllegalArgumentException> { LottoMatcher(winningLotto, bonusNumber) }
        assertThat(exception.message).isEqualTo("[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다.")
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 6개인 경우 1등이 된다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val publishedLotto = parseLottoNumber(numbers)
        val rank = lottoMatcher.matchLotto(listOf(publishedLotto))
        assertThat(rank).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 5개이고, 보너스 볼이 일치하는 경우 2등이 된다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 7)
        val publishedLotto = parseLottoNumber(numbers)
        val rank = lottoMatcher.matchLotto(listOf(publishedLotto))
        assertThat(rank).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 5개인 경우 3등이 된다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 8)
        val publishedLotto = parseLottoNumber(numbers)
        val rank = lottoMatcher.matchLotto(listOf(publishedLotto))
        assertThat(rank).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 4개인 경우 4등이 된다`() {
        val numbers = listOf(1, 2, 3, 4, 8, 9)
        val publishedLotto = parseLottoNumber(numbers)
        val rank = lottoMatcher.matchLotto(listOf(publishedLotto))
        assertThat(rank).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 3개인 경우 5등이 된다`() {
        val numbers = listOf(1, 2, 3, 8, 9, 10)
        val publishedLotto = parseLottoNumber(numbers)
        val rank = lottoMatcher.matchLotto(listOf(publishedLotto))
        assertThat(rank).isEqualTo(Rank.FIFTH)
    }

    @ParameterizedTest
    @MethodSource("failedNumbersProvider")
    fun `당첨 번호와 일치하는 번호가 3개 미만인 경우 탈락된다`(numbers: List<Int>) {
        val publishedLotto = parseLottoNumber(numbers)
        val rank = lottoMatcher.matchLotto(listOf(publishedLotto))
        assertThat(rank).isEqualTo(Rank.MISS)
    }

    private fun parseLottoNumber(numbers: List<Int>): Lotto = Lotto(LottoNumbers(numbers.map { number -> LottoNumber(number) }))

    companion object {
        @JvmStatic
        fun failedNumbersProvider(): Stream<List<Int>> =
            Stream.of(
                listOf(1, 2, 8, 9, 10, 11),
                listOf(1, 8, 9, 10, 11, 12),
                listOf(8, 9, 10, 11, 12, 13),
            )
    }
}
