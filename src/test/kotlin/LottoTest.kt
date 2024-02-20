package lotto

import model.Lotto
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


class LottoTest {
    @ParameterizedTest
    @MethodSource("invalidateLottoSizeExample")
    fun `로또 번호의 개수가 6개가 아니면 예외가 발생한다`(lotto:List<Int>) {
        assertThrows<IllegalArgumentException> {
            Lotto(lotto)
        }
    }

    @ParameterizedTest
    @MethodSource("validateLottoSizeExample")
    fun `로또 번호의 개수가 6개면 예외가 발생하지 않는다`(lotto:List<Int>) {
        assertDoesNotThrow { Lotto(lotto) }
        }


    companion object{
        @JvmStatic
        fun invalidateLottoSizeExample() =
            listOf(
                Arguments.of(listOf(1,2,3,4,5)),
                Arguments.of(listOf(1,2,3,4,5,6,7)),
            )

        @JvmStatic
        fun validateLottoSizeExample() =
            listOf(
                Arguments.of(listOf(1,2,3,4,5,6)),
                Arguments.of(listOf(7,8,9,10,11,12)),
            )
    }
}
