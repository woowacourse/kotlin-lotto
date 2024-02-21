package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoNumberMatcherTest {

    @ParameterizedTest
    @MethodSource("lottoNumbers, expectedCount")
    fun `두 로또의 일치 숫자 갯수를 반환 한다`(lottoNumbers: List<Int>, expectedCount: Int) {
        val lottoNumberMatcher = LottoNumberMatcher()
        val targetLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })
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
