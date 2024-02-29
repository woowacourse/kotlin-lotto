package model.lottery

import model.Quantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LotteryTest {
    @Test
    fun `중복이 없는 6 개의 1 ~ 45 사이 숫자로 로또를 생성한다`() {
        assertDoesNotThrow {
            Lottery.of(1, 2, 3, 4, 5, 6)
        }
    }

    @Test
    fun `로또 번호 6개만을 저장한다`() {
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        assertThat(lottery.lotteryNumbers.size).isEqualTo(6)
    }

    @Test
    fun `로또 번호 6개가 아니면 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            Lottery.of(1, 2, 3, 4, 5)
        }
    }

    @Test
    fun `로또 번호에 중복된 번호가 없어야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lottery.of(1, 2, 2, 4, 5, 6)
        }
    }

    @Test
    fun `로또 번호를 당첨 번호와 비교하여 서로 같은 번호를 센다`() {
        val lottery = Lottery.of(3, 6, 9, 12, 15, 18)
        val winningLottery = Lottery.of(2, 4, 6, 8, 10, 12)

        val actual = lottery.compareLottery(winningLottery)
        assertThat(actual).isEqualTo(Quantity(2))
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함한다면 참이다`() {
        val lottery = Lottery.of(4, 8, 12, 16, 20, 24)
        val bonusNumber = LotteryNumber(20)
        assertTrue(bonusNumber in lottery)
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하지 않는다면 거짓이다`() {
        val lottery = Lottery.of(2, 4, 6, 8, 10, 12)
        val bonusNumber = LotteryNumber(21)
        assertFalse(bonusNumber in lottery)
    }
}
