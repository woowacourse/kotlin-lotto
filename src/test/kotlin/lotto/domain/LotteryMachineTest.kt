package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LotteryMachineTest {

    @Test
    fun `구매 횟수에 맞는 개수의 로또를 정상적으로 발행한다`() {
        val machine: LotteryMachine = LotteryMachine()
        assertDoesNotThrow { machine.generateRandomLotteries(5) }
    }

    @Test
    fun `구매 횟수에 맞는 개수의 로또를 발행한다`() {
        val machine: LotteryMachine = LotteryMachine()
        assertThat(machine.generateRandomLotteries(2).size).isEqualTo(2)
    }

    @Test
    fun `랜덤 번호들을 발행하여 로또를 생성할 수 있다`() {
        val machine: LotteryMachine = LotteryMachine()
        assertDoesNotThrow {
            val lotteries = machine.generateRandomLotteries(1)
            assertThat(lotteries.size).isEqualTo(1)
        }
    }
}
