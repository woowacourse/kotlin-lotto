package lotto

import lotto.model.Lotto
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @ParameterizedTest
    @MethodSource("유효하지 않은 로또 번호 테스트 데이터")
    fun `로또 번호가 잘못된 경우 예외가 발생한다`(lottoNumbers: List<Int>) =
        assertThrows<IllegalArgumentException> {
            Lotto(lottoNumbers)
        }

    @ParameterizedTest
    @MethodSource("유효한 로또 번호 테스트 데이터")
    fun `로또 번호가 올바른 경우 예외가 발생하지 않는다`(lottoNumbers: List<Int>) =
        assertDoesNotThrow {
            Lotto(lottoNumbers)
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
    }
}
