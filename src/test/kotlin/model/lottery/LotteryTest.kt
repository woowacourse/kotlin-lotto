package model.lottery

import model.lottery.strategy.ExplicitNumbersStrategy
import model.lottery.strategy.RandomNumbersStrategy
import org.assertj.core.api.Assertions.assertThat
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
    fun `명시된 숫자를 생성하여 로또를 만든다`() {
        val actualLottery = Lottery.from(ExplicitNumbersStrategy(listOf(1, 3, 7, 5, 11, 9)))
        assertThat(actualLottery).isEqualTo(Lottery.of(listOf(1, 3, 5, 7, 9, 11)))
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
