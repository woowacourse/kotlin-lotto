package model

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
}
