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
                    DEFAULT_LOTTERY,
                    DEFAULT_LOTTERY,
                ),
            )
        }
    }

    @Test
    fun `로또가 2장 저장되면 lotteries의 크기는 2이다`() {
        val lotteries =
            Lotteries(
                listOf(
                    DEFAULT_LOTTERY,
                    DEFAULT_LOTTERY,
                ),
            )
        assertThat(lotteries.lotteries.size).isEqualTo(2)
    }

    companion object {
        private val DEFAULT_LOTTERY = Lottery.of(1, 2, 3, 4, 5, 6)
    }
}
