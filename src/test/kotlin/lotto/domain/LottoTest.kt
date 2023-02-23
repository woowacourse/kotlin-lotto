package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    private fun Lotto(numbers: List<Int>): Lotto {
        return Lotto(numbers.map { LottoNumber(it) })
    }

    @Test
    fun `로또는 번호 여섯 개를 가져야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5))
        }
    }

    @Test
    fun `로또는 중복 되지 않는 숫자를 가져야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 2, 3, 4, 5))
        }
    }

    @MethodSource("matchingCountNumbers")
    @ParameterizedTest
    fun `당첨 번호와 몇개 일치 하는지 판단 한다`(numbers: List<Int>, matchCount: Int) {
        val lotto = Lotto(numbers)
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThat(lotto.countMatchingNumbers(winningLotto)).isEqualTo(matchCount)
    }

    @MethodSource("matchingBonusNumber")
    @ParameterizedTest
    fun `보너스 번호와 일치하는지 판단한다`(numbers: List<Int>, bonusNumber: LottoNumber, isCorrect: Boolean) {
        val lotto = Lotto(numbers)
        assertThat(lotto.checkMatchingBonusNumber(bonusNumber)).isEqualTo(isCorrect)
    }

    companion object {
        @JvmStatic
        fun matchingCountNumbers(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(7, 8, 9, 10, 11, 12), 0
                ),
                Arguments.of(
                    listOf(1, 7, 8, 9, 10, 11), 1
                ),
                Arguments.of(
                    listOf(1, 2, 7, 8, 9, 10), 2
                ),
                Arguments.of(
                    listOf(1, 2, 3, 7, 8, 9), 3
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4, 7, 8), 4
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4, 5, 7), 5
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4, 5, 6), 6
                )
            )
        }

        @JvmStatic
        fun matchingBonusNumber(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(1, 2, 3, 4, 5, 6), LottoNumber(3), true
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4, 5, 6), LottoNumber(7), false
                )
            )
        }
    }
}
