package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LotteryTest {
    @Test
    fun `중복이 없는 6 개의 1 ~ 45 사이 숫자로 로또를 생성한다`() {
        assertDoesNotThrow {
            SUCCESS_LOTTERY
        }
    }

    @Test
    fun `로또 번호 6개만을 저장한다`() {
        assertThat(SUCCESS_LOTTERY.lotteryNumbers.size).isEqualTo(DEFAULT_LOTTERY_SIZE)
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

    companion object {
        private val SUCCESS_LOTTERY = Lottery.of(1, 2, 3, 4, 5, 6)
        private const val DEFAULT_LOTTERY_SIZE = 6
    }
}
