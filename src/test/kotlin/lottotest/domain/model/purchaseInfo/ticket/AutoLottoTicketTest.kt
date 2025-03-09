package lottotest.domain.model.purchaseInfo.ticket

import lotto.domain.model.purchaseInfo.ticket.AutoLottoTicket
import lotto.domain.model.purchaseInfo.ticket.LottoTicket
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutoLottoTicketTest {
    @Test
    fun `자동 로또 티켓은 로또 티켓 한장의 사이즈 만큼의 로또 번호들을 갖는다`() {
        // when then
        assertThat(AutoLottoTicket().lottoNumbers.size).isEqualTo(LottoTicket.LOTTO_TICKET_SIZE)
    }
}
