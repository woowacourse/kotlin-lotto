package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLotteryTicketsMachineTest {
    @Test
    fun `수동 로또를 발행한다`() {
        val numbers = listOf(
            listOf(1, 3, 5, 7, 9, 11),
            listOf(45, 24, 33, 10, 5, 15)
        )
        val ticketsMachine = ManualLotteryTicketsMachine(numbers)
        val expectedLottery1 = Lottery.from(listOf(1, 3, 5, 7, 9, 11))
        val expectedLottery2 = Lottery.from(listOf(45, 24, 33, 10, 5, 15))

        val tickets = ticketsMachine.generate()

        assertThat(tickets[0].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery1.numbers)
        assertThat(tickets[1].numbers).containsExactlyInAnyOrderElementsOf(expectedLottery2.numbers)
    }
}
