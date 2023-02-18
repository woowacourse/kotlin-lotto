package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @Test
    fun `로또는 번호 여섯 개를 가져야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                TestNumberGenerator(
                    listOf(
                        LottoNumber(1), LottoNumber(2), LottoNumber(3),
                        LottoNumber(4), LottoNumber(5)
                    )
                ).generate()
            )
        }
    }

    @Test
    fun `로또는 중복 되지 않는 숫자를 가져야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                TestNumberGenerator(
                    listOf(
                        LottoNumber(4), LottoNumber(3), LottoNumber(1), LottoNumber(2), LottoNumber(2),
                        LottoNumber(
                            2
                        )
                    )
                ).generate()
            )
        }
    }

    @MethodSource("matchingCountNumbers")
    @ParameterizedTest
    fun `당첨 번호와 몇개 일치 하는지 판단 한다`(numbers: List<LottoNumber>, matchCount: Int) {
        val lotto = Lotto(numbers)
        val winningLotto = Lotto(
            listOf(
                LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6)
            )
        )

        assertThat(lotto.countMatchingNumbers(winningLotto)).isEqualTo(matchCount)
    }

    @MethodSource("matchingBonusNumber")
    @ParameterizedTest
    fun `보너스 번호와 일치하는지 판단한다`(numbers: List<LottoNumber>, bonusNumber: LottoNumber, isCorrect: Boolean) {
        val lotto = Lotto(numbers)
        assertThat(lotto.checkMatchingBonusNumber(bonusNumber)).isEqualTo(isCorrect)
    }

    companion object {
        @JvmStatic
        fun matchingCountNumbers(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(
                        LottoNumber(7), LottoNumber(8), LottoNumber(9), LottoNumber(10), LottoNumber(11), LottoNumber(12)
                    ),
                    0
                ),
                Arguments.of(
                    listOf(
                        LottoNumber(1), LottoNumber(7), LottoNumber(8), LottoNumber(9), LottoNumber(10), LottoNumber(11)
                    ),
                    1
                ),
                Arguments.of(
                    listOf(
                        LottoNumber(1), LottoNumber(2), LottoNumber(7), LottoNumber(8), LottoNumber(9), LottoNumber(10)
                    ),
                    2
                ),
                Arguments.of(
                    listOf(
                        LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(7), LottoNumber(8), LottoNumber(9)
                    ),
                    3
                ),
                Arguments.of(
                    listOf(
                        LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(7), LottoNumber(8)
                    ),
                    4
                ),
                Arguments.of(
                    listOf(
                        LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(7)
                    ),
                    5
                ),
                Arguments.of(
                    listOf(
                        LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6)
                    ),
                    6
                )

            )
        }

        @JvmStatic
        fun matchingBonusNumber(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6)
                    ),
                    LottoNumber(3), true
                ),
                Arguments.of(
                    listOf(
                        LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5),
                        LottoNumber(
                            6
                        )
                    ),
                    LottoNumber(7), false
                )
            )
        }
    }

    class TestNumberGenerator(private val numbers: List<LottoNumber>) : LottoNumberGenerator {
        override fun generate(): List<LottoNumber> {
            return numbers
        }
    }
}
