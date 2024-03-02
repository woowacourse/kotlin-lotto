package model.lottery

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LotteryTest {
    @Test
    fun `로또를 수동으로 생성한다`() {
        assertDoesNotThrow { Lottery.of(1, 2, 3, 4, 5, 6) }
    }

    @RepeatedTest(1000)
    fun `로또를 자동으로 생성한다`() {
        assertDoesNotThrow { Lottery.fromRandom() }
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하지 않는다면 보너스 매치가 거짓이다`() {
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val bonusNumber = LotteryNumber(21)
        assertFalse(bonusNumber in lottery)
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하면 보너스 매치가 참이다`() {
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val bonusNumber = LotteryNumber(21)
        assertFalse(bonusNumber in lottery)
    }
}
