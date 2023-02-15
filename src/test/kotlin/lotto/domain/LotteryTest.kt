package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LotteryTest {
    @Test
    fun `6개의 로또번호를 가진다`() {
        val lotteryNumbers = listOf(1, 10, 20, 30, 40, 45)
        val lottery = Lottery(lotteryNumbers)

        assertThat(lottery.numbers.size).isEqualTo(6)
    }

    @ParameterizedTest
    @MethodSource("lotteryNumbersErrorCase")
    fun `로또번호가 6개가 아니면 에러가 발생한다`(numbers: List<Int>) {
        assertThrows<IllegalArgumentException> { Lottery(numbers) }
    }

    @Test
    fun `6개의 로또번호에 중복이 있으면 에러가 발생한다`() {
        val lotteryNumbers = listOf(10, 20, 30, 40, 10, 5)

        assertThrows<IllegalArgumentException> { Lottery(lotteryNumbers) }
    }

    companion object {
        @JvmStatic
        fun lotteryNumbersErrorCase(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf(1, 10, 20, 30, 40)),
                Arguments.of(listOf(1, 2, 10, 20, 30, 40, 45))
            )
        }
    }
}
