package model.lottery

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LottoBonusNumberCheckerTest {
    @Test
    fun `보너스 번호가 로또에 포함되면 참이다`() {
        val lottoBonusNumberChecker = LottoBonusNumberChecker()
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val bonusNumber = LotteryNumber(3)

        assertTrue(lottoBonusNumberChecker.containsBonusNumber(lottery, bonusNumber))
    }

    @Test
    fun `보너스 번호가 로또에 포함되면 거짓이다`() {
        val lottoBonusNumberChecker = LottoBonusNumberChecker()
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val bonusNumber = LotteryNumber(7)

        assertFalse(lottoBonusNumberChecker.containsBonusNumber(lottery, bonusNumber))
    }
}
