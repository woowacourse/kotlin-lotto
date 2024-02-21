package lotto

import lotto.model.LottoNumbers
import model.LottoNumber
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoNumbersTest {
    @ParameterizedTest
    @MethodSource("invalidLottoSizeExample")
    fun `로또 번호의 개수가 6개가 아니면 예외가 발생한다`(lotto: List<LottoNumber>) {
        assertThrows<IllegalArgumentException> {
            LottoNumbers(lotto)
        }
    }

    @ParameterizedTest
    @MethodSource("validLottoSizeExample")
    fun `로또 번호의 개수가 6개면 예외가 발생하지 않는다`(lotto: List<LottoNumber>) {
        assertDoesNotThrow { LottoNumbers(lotto) }
    }

    @ParameterizedTest
    @MethodSource("invalidLottoDuplicateExample")
    fun `로또 번호에 중복이 있을 경우 예외가 발생한다`(lotto: List<LottoNumber>) {
        assertThrows<IllegalArgumentException> {
            LottoNumbers(lotto)
        }
    }

    @ParameterizedTest
    @MethodSource("validLottoDuplicateExample")
    fun `로또 번호에 중복이 없을 경우 예외가 발생하지 않는다`(lotto: List<LottoNumber>) {
        assertDoesNotThrow { LottoNumbers(lotto) }
    }

    companion object {
        private fun lottoNumbersOf(vararg numbers: Int): List<LottoNumber> {
            return numbers.map { LottoNumber(it) }
        }

        @JvmStatic
        fun invalidLottoSizeExample() =
            listOf(
                Arguments.of(lottoNumbersOf(1, 2, 3, 4, 5)),
                Arguments.of(lottoNumbersOf(1, 2, 3, 4, 5, 6, 7)),
            )

        @JvmStatic
        fun validLottoSizeExample() =
            listOf(
                Arguments.of(lottoNumbersOf(1, 2, 3, 4, 5, 6)),
                Arguments.of(lottoNumbersOf(7, 8, 9, 10, 11, 12)),
            )

        @JvmStatic
        fun invalidLottoDuplicateExample() =
            listOf(
                Arguments.of(lottoNumbersOf(1, 2, 3, 4, 4, 5)),
                Arguments.of(lottoNumbersOf(7, 8, 9, 9, 11, 11)),
            )

        @JvmStatic
        fun validLottoDuplicateExample() =
            listOf(
                Arguments.of(lottoNumbersOf(1, 2, 3, 4, 5, 6)),
                Arguments.of(lottoNumbersOf(7, 8, 9, 10, 11, 12)),
            )
    }
}
