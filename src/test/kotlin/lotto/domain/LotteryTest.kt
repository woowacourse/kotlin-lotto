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
    return Lottery(numbers.map { LotteryNumber.from(it) })
}

class LotteryTest {
    @Test
    fun `6개의 로또번호를 가진다`() {
        val lottery = Lottery(1, 10, 20, 30, 40, 45)

        assertThat(lottery.numbers.size).isEqualTo(6)
    }

    @ParameterizedTest(name = "로또 번호는 {0}개일 수 없다")
    @MethodSource("lotteryNumbersErrorCase")
    fun `로또번호가 6개여야 한다`(length: Int, numbers: IntArray) {
        assertThrows<IllegalArgumentException> { Lottery(*numbers) }
    }

    @Test
    fun `6개의 로또번호에 중복이 있으면 안 된다`() {
        assertThrows<IllegalArgumentException> { Lottery(10, 20, 30, 40, 10, 5) }
    }

    @Test
    fun `당첨 번호와 매치하는 로또번호가 몇 개인지 확인한다`() {
        val lottery = Lottery(1, 10, 20, 30, 40, 45)
        val winningLottery = Lottery(1, 15, 20, 35, 40, 44)

        assertThat(lottery.countMatches(winningLottery)).isEqualTo(3)
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource("보너스 번호가 로또 번호에 포함되어 있다, 5, true", "보너스 번호가 로또 번호에 포함되어 있지 않다, 10, false")
    fun `보너스번호가 로또번호에 포함되어 있는지 확인한다`(testName: String, bonusNumber: Int, expected: Boolean) {
        val bonusNumber = LotteryNumber.from(bonusNumber)
        val lottery = Lottery(1, 2, 3, 4, 5, 6)

        assertThat(lottery.containBonusNumber(bonusNumber)).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun lotteryNumbersErrorCase(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    5,
                    intArrayOf(1, 20, 30, 40, 50)
                ),
                Arguments.of(
                    7,
                    intArrayOf(1, 2, 10, 20, 30, 40, 45),
                )
            )
        }
    }
}
