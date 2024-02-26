package model.lottery

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LotteryMachineTest {
    private val lotteryMachine = LotteryMachine()

    @RepeatedTest(1_000)
    fun `6자리의 중복되지 않은 번호의 로또를 자동으로 생성한다`() {
        assertDoesNotThrow {
            println(lotteryMachine.generateRandomLottery())
        }
    }

    @Test
    fun `6자리의 중복되지 않은 번호의 로또를 수동으로 생성한다`() {
        val lottery = lotteryMachine.generateManualLottery("1,3,5,7,9,11")
        assertThat(lottery).isEqualTo(Lottery.of(1, 3, 5, 7, 9, 11))
    }
}
