package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotteriesGeneratorTest {
    @Test
    fun `구입 로또 개수만큼 로또를 2개 발행한다`() {
        val numbers = mutableListOf(
            listOf(1, 3, 5, 7, 9, 11),
            listOf(45, 24, 33, 10, 5, 15)
        )
        val generator = LotteriesGenerator()
        val expectedLottery1 = listOf(
            LotteryNumber(1), LotteryNumber(3), LotteryNumber(5),
            LotteryNumber(7), LotteryNumber(9), LotteryNumber(11)
        )
        val expectedLottery2 = listOf(
            LotteryNumber(5), LotteryNumber(10), LotteryNumber(15),
            LotteryNumber(24), LotteryNumber(33), LotteryNumber(45)
        )

        val lotteries = generator.generate(2) {
            numbers.removeAt(0)
        }

        assertThat(lotteries[0].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery1)
        assertThat(lotteries[1].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery2)
    }
}
