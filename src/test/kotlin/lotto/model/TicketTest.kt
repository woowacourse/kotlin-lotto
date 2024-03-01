package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun `수동으로 입력한 로또 번호를 포함하여 티켓을 발행한다`() {
        val lottoCount = LottoCount(PURCHASE_PRICE, LOTTO_PRICE, 1)
        val ticket = Ticket(lottoCount)
        val lottos = ticket.issueLottos(listOf(MANUAL_LOTTOS))
        assertThat(lottos.size).isEqualTo(14)
        assertThat(lottos).contains(MANUAL_LOTTOS)
    }

    companion object {
        private const val PURCHASE_PRICE = 14000
        private const val LOTTO_PRICE = 1000
        private val MANUAL_LOTTOS = Lotto(listOf(1, 2, 3, 4, 5, 6))
    }
}
