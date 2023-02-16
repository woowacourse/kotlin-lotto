package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LotteriesGeneratorTest {
    @ParameterizedTest
    @MethodSource("generateLotteries")
    fun `구입 로또 개수만큼 로또를 발행한다`(
        numbers: MutableList<Int>,
        expectedLottery1: List<LotteryNumber>,
        expectedLottery2: List<LotteryNumber>
    ) {
        val generator = LotteriesGenerator()

        val lotteries = generator.generate(
            {
                numbers.removeAt(0)
            }, 2
        )

        assertThat(lotteries[0].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery1)
        assertThat(lotteries[1].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery2)
    }

    companion object {
        @JvmStatic
        fun generateLotteries(): Array<Arguments> {
            return arrayOf(
                Arguments.of(
                    mutableListOf(
                        1, 3, 5, 7, 9, 11,
                        45, 24, 33, 10, 5, 15
                    ),
                    listOf(
                        LotteryNumber(1), LotteryNumber(3), LotteryNumber(5),
                        LotteryNumber(7), LotteryNumber(9), LotteryNumber(11)
                    ),
                    listOf(
                        LotteryNumber(5), LotteryNumber(10), LotteryNumber(15),
                        LotteryNumber(24), LotteryNumber(33), LotteryNumber(45)
                    )
                ),
                Arguments.of(
                    mutableListOf(
                        1, 3, 5, 5, 7, 8, 12,
                        42, 24, 33, 10, 5, 33, 20
                    ),
                    listOf(
                        LotteryNumber(1), LotteryNumber(3), LotteryNumber(5),
                        LotteryNumber(7), LotteryNumber(8), LotteryNumber(12)
                    ),
                    listOf(
                        LotteryNumber(5), LotteryNumber(10), LotteryNumber(20),
                        LotteryNumber(24), LotteryNumber(33), LotteryNumber(42)
                    )
                )
            )
        }
    }
}
