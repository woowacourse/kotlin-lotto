package model.lottery

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotteriesTest {
    @Test
    fun `로또 여러 장을 저장할 수 있다`() {
        val lotteries =
            Lotteries(
                listOf(
                    Lottery.of(1, 2, 3, 4, 5, 6),
                    Lottery.of(1, 2, 3, 4, 5, 6),
                ),
            )
        assertThat(lotteries.lotteries.size).isEqualTo(2)
    }

    @Test
    fun `로또 뭉치들을 합쳐서 하나의 로또 뭉치로 만들 수 있다`() {
        val lotteries =
            Lotteries(
                listOf(
                    Lottery.of(1, 3, 5, 7, 9, 11),
                    Lottery.of(2, 4, 6, 8, 10, 12),
                ),
            )
        val otherLotteries =
            Lotteries(
                listOf(
                    Lottery.of(2, 4, 6, 8, 10, 12),
                    Lottery.of(3, 6, 9, 12, 15, 18),
                ),
            )
        assertThat(lotteries + otherLotteries).isEqualTo(
            Lotteries(
                listOf(
                    Lottery.of(1, 3, 5, 7, 9, 11),
                    Lottery.of(2, 4, 6, 8, 10, 12),
                    Lottery.of(2, 4, 6, 8, 10, 12),
                    Lottery.of(3, 6, 9, 12, 15, 18),
                ),
            ),
        )
    }
}
