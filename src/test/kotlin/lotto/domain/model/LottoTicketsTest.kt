package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTicketsTest {

    @Test
    fun `로또 티켓 수량이 5개일 때 size로 5를 반환한다`() {
        val tickets = LottoTickets(
            listOf(
                Lotto.from(listOf(1, 2, 3, 4, 5, 6)),
                Lotto.from(listOf(1, 2, 3, 4, 5, 6)),
                Lotto.from(listOf(1, 2, 3, 4, 5, 6)),
                Lotto.from(listOf(1, 2, 3, 4, 5, 6)),
                Lotto.from(listOf(1, 2, 3, 4, 5, 6))
            )
        )
        val actual = tickets.size
        assertThat(actual).isEqualTo(5)
    }

    @Test
    fun `두 LottoTickets을 하나의 LottoTickets로 합칠 수 있다`() {
        val ticket1 = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val ticket2 = Lotto.from(listOf(7, 8, 9, 10, 11, 12))
        val tickets1 = LottoTickets(listOf(ticket1))
        val tickets2 = LottoTickets(listOf(ticket2))

        val actual = tickets1.concat(tickets2).tickets
        val expected = LottoTickets(listOf(ticket1, ticket2)).tickets
        assertThat(actual).isEqualTo(expected)
    }
}
