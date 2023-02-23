package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun Lottery(vararg numbers: Int): Lottery {
    return Lottery(numbers.map { LotteryNumber.from(it) })
}

class LotteriesGeneratorTest {
    @Test
    fun `구입 로또 개수만큼 로또를 2개 발행한다`() {
        val lotteryGenerator = TestLotteryGenerator()
        val generator = LotteriesGenerator()
        val expectedLottery1 = listOf(
            LotteryNumber.from(1), LotteryNumber.from(3), LotteryNumber.from(5),
            LotteryNumber.from(7), LotteryNumber.from(9), LotteryNumber.from(11)
        )
        val expectedLottery2 = listOf(
            LotteryNumber.from(5), LotteryNumber.from(10), LotteryNumber.from(15),
            LotteryNumber.from(24), LotteryNumber.from(33), LotteryNumber.from(45)
        )

        val lotteries = generator.generate(2, lotteryGenerator)

        assertThat(lotteries[0].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery1)
        assertThat(lotteries[1].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery2)
    }

    class TestLotteryGenerator : LotteryGenerator {
        val lotteries = mutableListOf(
            Lottery(1, 3, 5, 7, 9, 11),
            Lottery(45, 24, 33, 10, 5, 15)
        )

        override fun generate(): Lottery {
            return lotteries.removeAt(0)
        }
    }
}
