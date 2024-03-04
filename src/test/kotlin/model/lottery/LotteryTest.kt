package model.lottery

import model.lottery.strategy.RandomNumbersStrategy
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LotteryTest {
    @RepeatedTest(50)
    fun `랜덤 숫자 생성기로 로또를 만든다`() {
        assertDoesNotThrow { Lottery.from(RandomNumbersStrategy) }
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하지 않는다면 보너스 매치가 거짓이다`() {
        val lottery = Lottery.of(listOf(4, 5, 6, 1, 2, 3))
        val bonusNumber = LotteryNumber.of(21)
        assertFalse(bonusNumber in lottery)
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하면 보너스 매치가 참이다`() {
        val lottery = Lottery.of(listOf(4, 5, 6, 1, 2, 3))
        val bonusNumber = LotteryNumber.of(21)
        assertFalse(bonusNumber in lottery)
    }
}
