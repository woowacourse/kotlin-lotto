package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotteryNumberTest {
    @Test
    fun `로또 번호 한개를 저장한다`() {
        val lotteryNumber = LotteryNumber(1)
        assertThat(lotteryNumber.number).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 44, 45])
    fun `1 이상 45 이하의 숫자여야 한다`(number: Int) {
        val lotteryNumber = LotteryNumber(number)
        assertTrue(lotteryNumber.number in 1..45)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46])
    fun `1 이상 45 이하의 숫자가 아니라면 예외를 발생시킨다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LotteryNumber(number)
        }
    }

    @Test
    fun `당첨번호와 중복되지 않은 범위 내의 보너스 번호를 생성할 수 있다`() {
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        assertDoesNotThrow {
            LotteryNumber.bonusNumber(lottery, 7)
        }
    }

    @Test
    fun `보너스 번호가 당첨번호와 중복되면 예외를 던진다`() {
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        assertThrows<IllegalArgumentException> {
            LotteryNumber.bonusNumber(lottery, 6)
        }
    }

    @Test
    fun `보너스 번호가 1~45의 범위를 벗어나면 예외를 던진다`() {
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        assertThrows<IllegalArgumentException> {
            LotteryNumber.bonusNumber(lottery, 46)
        }
    }
}
