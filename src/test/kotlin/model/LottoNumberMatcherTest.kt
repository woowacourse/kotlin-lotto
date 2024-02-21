package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoNumberMatcherTest {

    private val lottoNumberMatcher = LottoNumberMatcher()
    private val targetLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })

    @Test
    fun `로또가 보너스 넘버를 포함하면 true를 반환 한다`() {
        val bonusNumber = LottoNumber(1)
        val actual = lottoNumberMatcher.bonusCount(targetLotto, bonusNumber)

        assertThat(actual).isTrue()
    }

    @Test
    fun `로또가 보너스 넘버를 포함하면 false를 반환 한다`() {
        val bonusNumber = LottoNumber(7)
        val actual = lottoNumberMatcher.bonusCount(targetLotto, bonusNumber)

        assertThat(actual).isFalse()
    }

    @ParameterizedTest
    @MethodSource("lottoNumbers, expectedCount")
    fun `두 로또의 일치 숫자 갯수를 반환 한다`(lottoNumbers: List<Int>, expectedCount: Int) {
        val winningLotto = Lotto(lottoNumbers.map { LottoNumber(it) })
        val actual = lottoNumberMatcher.matchCount(targetLotto, winningLotto)

        assertThat(actual).isEqualTo(expectedCount)
    }

    companion object {
        @JvmStatic
        fun `lottoNumbers, expectedCount`(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(listOf(1, 2, 3, 4, 5, 6), 6),
                Arguments.arguments(listOf(2, 3, 4, 5, 6, 7), 5),
                Arguments.arguments(listOf(3, 4, 5, 6, 7, 8), 4),
                Arguments.arguments(listOf(4, 5, 6, 7, 8, 9), 3),
                Arguments.arguments(listOf(5, 6, 7, 8, 9, 10), 2),
                Arguments.arguments(listOf(6, 7, 8, 9, 10, 11), 1),
                Arguments.arguments(listOf(7, 8, 9, 10, 11, 12), 0)
            )
        }
    }
}
