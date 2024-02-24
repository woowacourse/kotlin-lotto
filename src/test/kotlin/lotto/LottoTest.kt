package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    private val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))

    @ParameterizedTest
    @MethodSource("유효한 로또 번호 테스트 데이터")
    fun `6개의 로또 번호가 올바른 경우 예외가 발생하지 않는다`(lottoNumbers: List<Int>) =
        assertDoesNotThrow {
            Lotto(lottoNumbers)
        }

    @ParameterizedTest
    @MethodSource("유효하지 않은 로또 번호 테스트 데이터")
    fun `6개의 로또 번호가 잘못된 경우 예외가 발생한다`(lottoNumbers: List<Int>) =
        assertThrows<IllegalArgumentException> {
            Lotto(lottoNumbers)
        }

    @ParameterizedTest
    @MethodSource("동일한 로또 번호 개수 테스트 데이터")
    fun `동일한 로또 번호의 개수를 구한다`(
        lottoNumbers: List<Int>,
        expected: Int,
    ) {
        // given
        val otherLotto = Lotto(lottoNumbers)

        // when
        val actual = lotto.getMatchingCount(otherLotto)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource("1, true", "2, true", "10, false", "25, false")
    fun `로또에 해당 로또 번호가 포함되어 있는지 판별한다`(
        number: Int,
        expected: Boolean,
    ) {
        // given
        val lottoNumber = LottoNumber(number)

        // when
        val actual = lotto.contains(lottoNumber)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `유효하지 않은 로또 번호 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(listOf(100, 200, 300, 400, 500, 600)),
                Arguments.of(listOf(1, 2, 3, 4, 5, 5)),
            )

        @JvmStatic
        fun `유효한 로또 번호 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6)),
                Arguments.of(listOf(7, 8, 9, 10, 11, 12)),
            )

        @JvmStatic
        fun `동일한 로또 번호 개수 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(listOf(1, 2, 3, 4, 5, 10), 5),
                Arguments.of(listOf(10, 11, 3, 4, 5, 6), 4),
                Arguments.of(listOf(1, 2, 3, 15, 16, 17), 3),
                Arguments.of(listOf(1, 2, 40, 41, 42, 43), 2),
                Arguments.of(listOf(1, 30, 31, 32, 33, 34), 1),
                Arguments.of(listOf(7, 8, 9, 10, 11, 12), 0),
            )
    }
}
