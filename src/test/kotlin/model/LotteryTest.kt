package model

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LotteryTest {
    @Test
    fun `로또 갯수가 6개가 아니면 예외 발생`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7)

        assertThatThrownBy {
            Lottery.fromList(numbers)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Lottery.EXCEPTION_INVALID_COUNT)
    }

    @Test
    fun `로또 번호에 중복이 있으면 예외 발생`() {
        val numbers = listOf(1, 2, 3, 4, 5, 5)
        assertThatThrownBy {
            Lottery.fromList(numbers)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Lottery.EXCEPTION_DUPLICATED_NUMBER)
    }

    @Test
    fun `로또 번호 일치 갯수 판별`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val winningLottery = Lottery.fromList(numbers)

        val expected = Lottery.fromList(listOf(1, 2, 3, 4, 5, 6))
        winningLottery.getCountOfMatch(expected) shouldBe 6
    }

    @Test
    fun `로또 보너스 번호 일치 판별`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val winningLottery = Lottery.fromList(numbers)

        val bonus = Bonus.fromInput("5", Lottery.fromList(listOf(7, 8, 9, 10, 11, 12)))
        winningLottery.hasBonus(bonus) shouldBe true
    }
}
