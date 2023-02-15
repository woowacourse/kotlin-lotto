package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotteryTest {
    @Test
    fun `6개의 로또번호를 가진다`() {
        val lotteryNumbers = listOf(1, 10, 20, 30, 40, 45)
        val lottery = Lottery(lotteryNumbers)

        assertThat(lottery.numbers.size).isEqualTo(6)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1,10,20,30,40", "1,2,10,20,30,40,45"])
    fun `로또번호가 6개가 아니면 에러가 발생한다`(numbers: String) {
        val lotteryNumbers = numbers.split(",").map { it.toInt() }

        assertThrows<IllegalArgumentException> { Lottery(lotteryNumbers) }
    }

    @Test
    fun `6개의 로또번호에 중복이 있으면 에러가 발생한다`() {
        val lotteryNumbers = listOf(10, 20, 30, 40, 10, 5)

        assertThrows<IllegalArgumentException> { Lottery(lotteryNumbers) }
    }
}
