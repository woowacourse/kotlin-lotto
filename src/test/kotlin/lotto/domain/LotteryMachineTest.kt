package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LotteryMachineTest {

    @Test
    fun `당첨 번호와 로또들을 받아 결과를 생성한다`() {

        val lotteries: List<Lottery> = listOf(
            Lottery(1, 10, 20, 30, 40, 45)
        )
        val winningLottery = WinningLottery(
            Lottery(2, 3, 4, 5, 6, 7),
            LotteryNumber(8)
        )
        val machine: LotteryMachine = LotteryMachine()

        assertDoesNotThrow {
            machine.createWinningResult(winningLottery, lotteries, PurchaseAmount(1000))
        }
    }
}
