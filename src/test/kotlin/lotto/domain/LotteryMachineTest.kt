package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotteryMachineTest {

    @Test
    fun `당첨 번호와 로또들을 받아 결과를 알려준다`() {
        val lotteryNumbers = listOf(
            LotteryNumber(1),
            LotteryNumber(10),
            LotteryNumber(20),
            LotteryNumber(30),
            LotteryNumber(40),
            LotteryNumber(45)
        )
        val winningNumbers = listOf(
            LotteryNumber(1),
            LotteryNumber(15),
            LotteryNumber(20),
            LotteryNumber(35),
            LotteryNumber(40),
            LotteryNumber(44)
        )
        val lotteries: List<Lottery> = listOf(Lottery(lotteryNumbers))
        val winningLottery = WinningLottery(Lottery(winningNumbers), LotteryNumber(5))

        val machine: LotteryMachine = LotteryMachine()
        val realResult = machine.createWinningResult(winningLottery, lotteries)
        val expectedResult: WinningResult =
            WinningResult(
                Rank.values().associateWith { 1 }.toMutableMap(),
                1000
            )

        assertThat(realResult).isEqualTo(expectedResult)
    }
}
