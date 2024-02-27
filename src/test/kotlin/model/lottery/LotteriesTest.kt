package model.lottery

import model.Quantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
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

    @Test
    fun `로또 번호를 당첨 번호와 비교하여 서로 같은 번호를 센다`() {
        val lottery = Lottery.of(3, 6, 9, 12, 15, 18)
        val winningLottery = Lottery.of(2, 4, 6, 8, 10, 12)

        val actual = lottery.compareLottery(winningLottery)
        assertThat(actual).isEqualTo(Quantity(2))
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함한다면 참이다`() {
        val lottery = Lottery.of(4, 8, 12, 16, 20, 24)
        val bonusNumber = LotteryNumber(20)
        assertTrue(lottery.contains(bonusNumber))
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하지 않는다면 거짓이다`() {
        val lottery = Lottery.of(2, 4, 6, 8, 10, 12)
        val bonusNumber = LotteryNumber(21)
        assertFalse(lottery.contains(bonusNumber))
    }
}
