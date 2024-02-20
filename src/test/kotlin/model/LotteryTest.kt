package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LotteryTest {
    @Test
    fun `로또 번호 6개만을 저장한다`() {
        val lottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(2),
                LotteryNumber(3),
                LotteryNumber(4),
                LotteryNumber(5),
                LotteryNumber(6),
            )
        )
        assertThat(lottery.lotteryNumbers.size).isEqualTo(6)
    }

    @Test
    fun `로또 번호 6개가 아니면 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            Lottery(
                listOf(
                    LotteryNumber(1),
                    LotteryNumber(2),
                    LotteryNumber(3),
                    LotteryNumber(4),
                    LotteryNumber(5),
                )
            )
        }
    }

    @Test
    fun `로또 번호에 중복된 번호가 없어야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lottery(
                listOf(
                    LotteryNumber(1),
                    LotteryNumber(2),
                    LotteryNumber(2),
                    LotteryNumber(4),
                    LotteryNumber(5),
                    LotteryNumber(6),
                )
            )
        }
    }

}
