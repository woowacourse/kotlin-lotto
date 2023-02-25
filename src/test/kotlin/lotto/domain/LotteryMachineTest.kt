package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LotteryMachineTest {

    @Test
    fun `당첨 번호와 로또들을 받아 결과를 생성한다`() {

        val lotteries: Lotteries = Lotteries(listOf(Lottery(1, 10, 20, 30, 40, 45)))
        val winningLottery = WinningLottery(
            Lottery(2, 3, 4, 5, 6, 7),
            LotteryNumber(8)
        )
        val machine: LotteryMachine = LotteryMachine()

        assertDoesNotThrow {
            machine.createWinningResult(winningLottery, lotteries, PurchaseAmount(1000, 0))
        }
    }

    @Test
    fun `구매 횟수에 맞는 개수의 로또를 정상적으로 발행한다`() {
        val machine: LotteryMachine = LotteryMachine()
        assertDoesNotThrow { machine.generateLotteries(5) }
    }

    @Test
    fun `구매 횟수에 맞는 개수의 로또를 발행한다`() {
        val machine: LotteryMachine = LotteryMachine()
        assertThat(machine.generateLotteries(2).size).isEqualTo(2)
    }
}
