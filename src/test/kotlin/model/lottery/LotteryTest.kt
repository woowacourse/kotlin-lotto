package model.lottery

import model.lottery.generator.ExplicitNumbersGenerator
import model.lottery.generator.RandomNumbersGenerator
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LotteryTest {
    @Test
    fun `로또를 수동으로 생성한다_리팩터링 이전`() {
        assertDoesNotThrow { Lottery.of(1, 2, 3, 4, 5, 6) }
    }

    @RepeatedTest(1000)
    fun `로또를 자동으로 생성한다_리팩터링 이전`() {
        assertDoesNotThrow { Lottery.fromRandom() }
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하지 않는다면 보너스 매치가 거짓이다_리팩터링 이전`() {
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val bonusNumber = LotteryNumber.of(21)
        assertFalse(bonusNumber in lottery)
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하면 보너스 매치가 참이다_리팩터링 이전`() {
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val bonusNumber = LotteryNumber.of(21)
        assertFalse(bonusNumber in lottery)
    }

    @Test
    fun `명시 숫자 생성기로 로또를 만든다`() {
        val numbersGenerator = ExplicitNumbersGenerator(listOf(1, 3, 2, 5, 6, 4))
        val actual = Lottery.of(numbersGenerator)
        Assertions.assertThat(actual).isEqualTo(Lottery.of(1, 2, 3, 4, 5, 6))
    }

    @RepeatedTest(50)
    fun `랜덤 숫자 생성기로 로또를 만든다`() {
        val numbersGenerator = RandomNumbersGenerator(6, 1..45)
        assertDoesNotThrow { Lottery.of(numbersGenerator) }
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하지 않는다면 보너스 매치가 거짓이다`() {
        val lottery = Lottery.of(ExplicitNumbersGenerator(listOf(4, 5, 6, 1, 2, 3)))
        val bonusNumber = LotteryNumber.of(21)
        assertFalse(bonusNumber in lottery)
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하면 보너스 매치가 참이다`() {
        val lottery = Lottery.of(ExplicitNumbersGenerator(listOf(4, 5, 6, 1, 2, 3)))
        val bonusNumber = LotteryNumber.of(21)
        assertFalse(bonusNumber in lottery)
    }
}
