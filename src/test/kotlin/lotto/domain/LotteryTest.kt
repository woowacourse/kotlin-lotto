package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

private fun Lottery(vararg numbers: Int): Lottery {
    return Lottery(numbers.map(::LotteryNumber))
}

class LotteryTest {
    @Test
    fun `6개의 로또번호를 가진다`() {
        val lottery = Lottery(1, 10, 20, 30, 40, 45)

        assertThat(lottery.numbers.size).isEqualTo(6)
    }

    @ParameterizedTest
    @MethodSource("lotteryNumbersErrorCase")
    fun `로또번호가 6개가 아니면 에러가 발생한다`(numbers: IntArray, testName: String) {
        println(testName)

        assertThrows<IllegalArgumentException> { Lottery(*numbers) }
    }

    @Test
    fun `6개의 로또번호에 중복이 있으면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { Lottery(10, 20, 30, 40, 10, 5) }
    }

    @Test
    fun `당첨 번호와 매치하는 로또번호가 몇 개인지 확인한다`() {
        val lottery = Lottery(1, 10, 20, 30, 40, 45)
        val winningLottery = Lottery(1, 15, 20, 35, 40, 44)

        assertThat(lottery.countMatches(winningLottery)).isEqualTo(3)
    }

    @ParameterizedTest
    @CsvSource("5, true, 보너스 번호가 로또 번호에 포함되어 있다", "10, false, 보너스 번호가 로또 번호에 포함되어 있지 않다")
    fun `보너스번호가 로또번호에 포함되어 있는지 확인한다`(bonusNumber: Int, expected: Boolean, testName: String) {
        println(testName)

        val bonusNumber = LotteryNumber(bonusNumber)
        val lottery = Lottery(1, 2, 3, 4, 5, 6)

        assertThat(lottery.containBonusNumber(bonusNumber)).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun lotteryNumbersErrorCase(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    intArrayOf(1, 20, 30, 40, 50),
                    "로또 번호는 5개일 수 없다"
                ),
                Arguments.of(
                    intArrayOf(1, 2, 10, 20, 30, 40, 45),
                    "로또 번호는 7개일 수 없다"
                )
            )
        }
    }
}
