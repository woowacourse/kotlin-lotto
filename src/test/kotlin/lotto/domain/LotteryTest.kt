package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LotteryTest {
    @Test
    fun `6개의 로또번호를 가진다`() {
        val lottery = Lottery(1, 10, 20, 30, 40, 45)

        assertThat(lottery.numbers.size).isEqualTo(6)
    }

    @Test
    fun `로또번호가 6개가 아니면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { Lottery(1, 10, 30, 40, 45) }
        assertThrows<IllegalArgumentException> { Lottery(1, 2, 3, 4, 5, 6, 7) }
    }

    @Test
    fun `6개의 로또번호에 중복이 있으면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { Lottery(10, 20, 30, 40, 10, 5) }
    }

    @Test
    fun `당첨 번호와 매치하는 로또번호가 몇 개인지 확인한다`() {
        val lottery = Lottery(1, 10, 20, 30, 40, 45)
        val winningLottery = WinningLottery(
            Lottery(1, 15, 20, 35, 40, 44),
            LotteryNumber(5)
        )

        assertThat(lottery.countMatches(winningLottery.lottery)).isEqualTo(3)
    }

    @ParameterizedTest
    @CsvSource("5, true", "10, false")
    fun `숫자를 받아 로또번호와 일치하는 것이 있는지 확인한다`(number: Int, expected: Boolean) {
        val lotteryNumber: LotteryNumber = LotteryNumber(number)
        val lottery: Lottery = Lottery(1, 2, 3, 4, 5, 6)
        assertThat(lottery.contains(lotteryNumber)).isEqualTo(expected)
    }
}
