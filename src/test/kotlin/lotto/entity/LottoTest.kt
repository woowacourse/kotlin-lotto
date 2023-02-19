package lotto.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @Test
    fun `생성된 로또의 번호 중 1에서 45 사이 숫자가 있다면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                setOf(
                    LottoNumber(0),
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5)
                )
            )
        }
    }

    @MethodSource("provideLottoCountNotSix")
    @ParameterizedTest
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5)
                )
            )
        }
    }

    @MethodSource("provideDuplicateLotto")
    @ParameterizedTest
    fun `로또 번호가 중복되면 예외가 발생한다`(lotto: Set<LottoNumber>) {
        assertThrows<IllegalArgumentException> { Lotto(lotto) }
    }

    companion object {
        @JvmStatic
        fun provideDuplicateLotto(): List<Arguments> {
            return listOf(
                Arguments.of(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5)
                    )
                ),
                Arguments.of(
                    setOf(
                        LottoNumber(41),
                        LottoNumber(41),
                        LottoNumber(42),
                        LottoNumber(43),
                        LottoNumber(44),
                        LottoNumber(45)
                    )
                )
            )
        }

        @JvmStatic
        fun provideLottoCountNotSix(): List<Arguments> {
            return listOf(
                Arguments.of(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5)
                    )
                ),
                Arguments.of(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                        LottoNumber(7)
                    )
                )
            )
        }
    }
}
