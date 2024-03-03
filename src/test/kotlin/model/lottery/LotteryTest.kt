package model.lottery

import model.lottery.strategy.ExplicitNumbersStrategy
import model.lottery.strategy.RandomNumbersStrategy
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LotteryTest {
    @Test
    fun `명시 숫자 생성기로 로또를 만든다`() {
        val numbersGenerator = ExplicitNumbersStrategy(listOf(1, 3, 2, 5, 6, 4))
        val actual = Lottery.of(numbersGenerator)
        Assertions.assertThat(actual.lotteryNumbers).isEqualTo(
            listOf(
                LotteryNumber.of(1),
                LotteryNumber.of(2),
                LotteryNumber.of(3),
                LotteryNumber.of(4),
                LotteryNumber.of(5),
                LotteryNumber.of(6),
            ),
        )
    }

    @RepeatedTest(50)
    fun `랜덤 숫자 생성기로 로또를 만든다`() {
        assertDoesNotThrow { Lottery.of(RandomNumbersStrategy) }
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하지 않는다면 보너스 매치가 거짓이다`() {
        val lottery = Lottery.of(ExplicitNumbersStrategy(listOf(4, 5, 6, 1, 2, 3)))
        val bonusNumber = LotteryNumber.of(21)
        assertFalse(bonusNumber in lottery)
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하면 보너스 매치가 참이다`() {
        val lottery = Lottery.of(ExplicitNumbersStrategy(listOf(4, 5, 6, 1, 2, 3)))
        val bonusNumber = LotteryNumber.of(21)
        assertFalse(bonusNumber in lottery)
    }
}
