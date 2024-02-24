package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LotteriesTest {
    @Test
    fun `2장 이상의 로또를 저장할 수 있다`() {
        assertDoesNotThrow {
            Lotteries(
                listOf(
                    Lottery.of(1, 2, 3, 4, 5, 6),
                    Lottery.of(1, 2, 3, 4, 5, 6),
                ),
            )
        }
    }

    @Test
    fun `로또가 2장 저장되면 lotteries의 크기는 2이다`() {
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
