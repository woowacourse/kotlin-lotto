package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LotteriesGeneratorTest {
    @ParameterizedTest
    @MethodSource("generateLotteries")
    fun `구입 로또 개수만큼 로또를 발행한다`(
        numbers: MutableList<List<Int>>,
        expectedLottery1: List<LotteryNumber>,
        expectedLottery2: List<LotteryNumber>,
        testName: String
    ) {
        println(testName)

        val generator = LotteriesGenerator()

        val lotteries = generator.generate(2) {
            numbers.removeAt(0)
        }

        assertThat(lotteries[0].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery1)
        assertThat(lotteries[1].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery2)
    }

    companion object {
        @JvmStatic
        fun generateLotteries(): Array<Arguments> {
            return arrayOf(
                Arguments.of(
                    mutableListOf(
                        listOf(1, 3, 5, 7, 9, 11),
                        listOf(45, 24, 33, 10, 5, 15)
                    ),
                    listOf(
                        LotteryNumber(1), LotteryNumber(3), LotteryNumber(5),
                        LotteryNumber(7), LotteryNumber(9), LotteryNumber(11)
                    ),
                    listOf(
                        LotteryNumber(5), LotteryNumber(10), LotteryNumber(15),
                        LotteryNumber(24), LotteryNumber(33), LotteryNumber(45)
                    ),
                    "로또 2개를 발행한다"
                )
            )
        }
    }
}
