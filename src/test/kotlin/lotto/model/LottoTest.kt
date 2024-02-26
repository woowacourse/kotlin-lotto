package lotto.model

import lotto.constants.LottoPrize
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @ParameterizedTest
    @MethodSource("유효하지 않은 로또 번호 테스트 데이터")
    fun `로또 번호가 잘못된 경우 예외가 발생한다`(lottoNumbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            Lotto(lottoNumbers)
        }
    }

    @ParameterizedTest
    @MethodSource("유효한 로또 번호 테스트 데이터")
    fun `로또 번호가 올바른 경우 예외가 발생하지 않는다`(lottoNumbers: List<Int>) {
        assertDoesNotThrow {
            Lotto(lottoNumbers)
        }
    }

    @ParameterizedTest
    @MethodSource("로또 당첨 결과 테스트 데이터")
    fun `로또의 당첨 결과를 확인한다`(
        otherLotto: List<Int>,
        bonusNumber: Int,
        expected: LottoPrize,
    ) {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))

        // when
        val actual = lotto.compare(Lotto(otherLotto), LottoNumber(bonusNumber))

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
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
        fun `로또 당첨 결과 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6), 7, LottoPrize.FIRST),
                Arguments.of(listOf(1, 2, 3, 4, 5, 7), 6, LottoPrize.SECOND),
                Arguments.of(listOf(1, 2, 3, 4, 5, 7), 8, LottoPrize.THIRD),
                Arguments.of(listOf(1, 2, 3, 4, 7, 8), 9, LottoPrize.FOURTH),
                Arguments.of(listOf(1, 2, 3, 7, 8, 9), 10, LottoPrize.FIFTH),
                Arguments.of(listOf(10, 11, 12, 13, 14, 15), 16, LottoPrize.NOTHING),
            )
    }
}
