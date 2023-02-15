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
            Lotto(TestNumberGenerator(listOf(1, 2, 3, 4, 5)).generate())
        }
    }

    @MethodSource("numbers")
    @ParameterizedTest
    fun `로또는 1~45 사이의 숫자를 가져야 한다`(numbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            Lotto(TestNumberGenerator(numbers).generate())
        }
    }

    @Test
    fun `로또는 중복 되지 않는 숫자를 가져야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(TestNumberGenerator(listOf(4, 3, 1, 2, 2, 2)).generate())
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
    fun `보너스 번호와 일치하는지 판단한다`(numbers: List<Int>, bonusNumber:Int, isCorrect:Boolean) {
        val lotto = Lotto(numbers)
        assertThat(lotto.checkMatchingBonusNumber(bonusNumber)).isEqualTo(isCorrect)
    }

    companion object {
        @JvmStatic
        fun numbers(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(0, 1, 2, 3, 4, 5), listOf(41, 42, 43, 44, 45, 46))
            )
        }

        @JvmStatic
        fun matchingCountNumbers(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(1, 2, 3, 7, 8, 9), 3),
                Arguments.of(listOf(1, 2, 3, 4, 8, 9), 4),
                Arguments.of(listOf(1, 2, 3, 4, 5, 9), 5),
                Arguments.of(listOf(1, 2, 3, 4, 5, 6), 6),
            )
        }

        @JvmStatic
        fun matchingBonusNumber(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6), 3, true),
                Arguments.of(listOf(1, 2, 3, 4, 5, 6), 7, false)
            )
        }
    }

    class TestNumberGenerator(private val numbers: List<Int>) : LottoNumberGenerator {
        override fun generate(): List<Int> {
            return numbers
        }
    }
}