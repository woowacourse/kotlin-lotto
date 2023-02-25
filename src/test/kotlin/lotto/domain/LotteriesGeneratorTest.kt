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
        val expectedLottery1 = Lottery(1, 3, 5, 7, 9, 11)
        val expectedLottery2 = Lottery(45, 24, 33, 10, 5, 15)

        val lotteries = generator.generate(2, lotteryGenerator)

        assertThat(lotteries[0].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery1.numbers)
        assertThat(lotteries[1].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery2.numbers)
    }

    @Test
    fun `수동 로또를 발행한다`() {
        val numbers = mutableListOf(
            listOf(1, 3, 5, 7, 9, 11),
            listOf(45, 24, 33, 10, 5, 15)
        )
        val lotteryGenerator = ManualLotteryGenerator(numbers)
        val generator = LotteriesGenerator()
        val expectedLottery1 = Lottery(1, 3, 5, 7, 9, 11)
        val expectedLottery2 = Lottery(45, 24, 33, 10, 5, 15)

        val lotteries = generator.generateManually(2, lotteryGenerator)

        assertThat(lotteries[0].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery1.numbers)
        assertThat(lotteries[1].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery2.numbers)
    }

    class TestLotteryGenerator : LotteryGenerator {
        private val lotteries = mutableListOf(
            Lottery(1, 3, 5, 7, 9, 11),
            Lottery(45, 24, 33, 10, 5, 15)
        )

        override fun generate(): Lottery {
            return lotteries.removeAt(0)
        }
    }
}
