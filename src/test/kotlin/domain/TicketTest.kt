package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun `티켓을 더하는 기능 확인`() {
        // given
        val ticket1 = Ticket(listOf(Lotto(1, 2, 3, 4, 5, 6)))
        val ticket2 = Ticket(listOf(Lotto(1, 2, 3, 4, 5, 8)))

        // when
        val ticket = ticket1.concatenateTicket(ticket2)

        // then
        assertThat(ticket.size).isEqualTo(2)
    }
}
