package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotteriesTest {
    @Test
    fun `로또 여러 장을 저장할 수 있다`() {
        val lotteries = Lotteries(
            listOf(
                Lottery(
                    listOf(
                        LotteryNumber(1),
                        LotteryNumber(2),
                        LotteryNumber(3),
                        LotteryNumber(4),
                        LotteryNumber(5),
                        LotteryNumber(6),
                    )
                ),
                Lottery(
                    listOf(
                        LotteryNumber(1),
                        LotteryNumber(2),
                        LotteryNumber(3),
                        LotteryNumber(4),
                        LotteryNumber(5),
                        LotteryNumber(6),
                    )
                )
            )
        )

        assertThat(lotteries.lotteries.size).isEqualTo(2)
    }
}
