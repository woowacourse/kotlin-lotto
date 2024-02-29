package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class TicketTest {
    @ParameterizedTest
    @CsvSource("1000,1", "10000,10")
    fun `구매할 수 있는 로또의 개수 계산한다`(
        purchasePrice: Int,
        amount: Int,
    ) {
        val ticket = Ticket(purchasePrice, TICKET_PRICE)
        assertThat(ticket.amount).isEqualTo(amount)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1000, 1000100])
    fun `로또의 구입 금액을 검증한다`(purchasePrice: Int) {
        assertThrows<IllegalArgumentException> {
            Ticket(purchasePrice, TICKET_PRICE)
        }
    }

    @Test
    fun `수동으로 입력한 로또 번호를 포함하여 티켓을 발행한다`() {
        val ticket = Ticket(2000, 1000)
        val lottos = ticket.issueLottos(listOf(Lotto(setOf(1, 2, 3, 4, 5, 6))))
        assertThat(lottos.size).isEqualTo(3)
    }

    companion object {
        private const val TICKET_PRICE = 1_000
    }
}
