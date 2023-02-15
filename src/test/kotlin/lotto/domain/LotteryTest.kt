package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LotteryTest {
    @Test
    fun `6개의 로또번호를 가진다`() {
        val lotteryNumbers = listOf(
            LotteryNumber(1),
            LotteryNumber(10),
            LotteryNumber(20),
            LotteryNumber(30),
            LotteryNumber(40),
            LotteryNumber(45)
        )
        val lottery = Lottery(lotteryNumbers)

        assertThat(lottery.numbers.size).isEqualTo(6)
    }

    @ParameterizedTest
    @MethodSource("lotteryNumbersErrorCase")
    fun `로또번호가 6개가 아니면 에러가 발생한다`(numbers: List<LotteryNumber>) {
        assertThrows<IllegalArgumentException> { Lottery(numbers) }
    }

    @Test
    fun `6개의 로또번호에 중복이 있으면 에러가 발생한다`() {
        val lotteryNumbers = listOf(
            LotteryNumber(10),
            LotteryNumber(20),
            LotteryNumber(30),
            LotteryNumber(40),
            LotteryNumber(10),
            LotteryNumber(5)
        )

        assertThrows<IllegalArgumentException> { Lottery(lotteryNumbers) }
    }

    @Test
    fun `당첨 번호와 매치하는 로또번호가 몇 개인지 확인한다`() {
        val lotteryNumbers = listOf(
            LotteryNumber(1),
            LotteryNumber(10),
            LotteryNumber(20),
            LotteryNumber(30),
            LotteryNumber(40),
            LotteryNumber(45)
        )
        val winningNumbers = listOf(
            LotteryNumber(1),
            LotteryNumber(15),
            LotteryNumber(20),
            LotteryNumber(35),
            LotteryNumber(40),
            LotteryNumber(44)
        )
        val lottery = Lottery(lotteryNumbers)
        val winningLottery = Lottery(winningNumbers)

        assertThat(lottery.countMatches(winningLottery)).isEqualTo(3)
    }

    @ParameterizedTest
    @CsvSource("5, true", "10, false")
    fun `보너스번호가 로또번호에 포함되어 있는지 확인한다`(bonusNumber: Int, expected: Boolean) {
        val bonusNumber = LotteryNumber(bonusNumber)
        val lottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(2),
                LotteryNumber(3),
                LotteryNumber(4),
                LotteryNumber(5),
                LotteryNumber(6)
            )
        )
        assertThat(lottery.containBonusNumber(bonusNumber)).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun lotteryNumbersErrorCase(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(
                        LotteryNumber(1),
                        LotteryNumber(10),
                        LotteryNumber(20),
                        LotteryNumber(30),
                        LotteryNumber(40)
                    )
                ),
                Arguments.of(
                    listOf(
                        LotteryNumber(1),
                        LotteryNumber(2),
                        LotteryNumber(10),
                        LotteryNumber(20),
                        LotteryNumber(30),
                        LotteryNumber(40),
                        LotteryNumber(45)
                    )
                )
            )
        }
    }
}
