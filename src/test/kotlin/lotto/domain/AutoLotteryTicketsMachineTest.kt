package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutoLotteryTicketsMachineTest {
    @Test
    fun `구입 로또 개수만큼 로또를 발행한다`() {
        val ticketsMachine = AutoLotteryTicketsMachine(3)

        val tickets = ticketsMachine.generate()

        assertThat(tickets.size).isEqualTo(3)
    }
}
