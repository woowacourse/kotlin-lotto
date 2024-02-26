package model.lottery

import WinningLottery
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningLotteryTest {
    @Test
    fun `보너스 번호가 당첨 번호에 포함된다면 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            WinningLottery(
                Lottery.of(1, 3, 5, 7, 9, 11),
                LotteryNumber(11),
            )
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호에 포함되지 않으면 통과한다`() {
        assertDoesNotThrow {
            WinningLottery(
                Lottery.of(1, 3, 5, 7, 9, 11),
                LotteryNumber(13),
            )
        }
    }
}
