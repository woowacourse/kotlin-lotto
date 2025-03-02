package lottotest.domain.model

import lotto.domain.model.AutoLottoTicket
import lotto.domain.model.LottoTicket
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutoLottoTicketTest {
    @Test
    fun `인스턴스가 생성되면 로또 티켓 한장의 사이즈 만큼의 로또 번호들을 갖는다`() {
        // when then
        assertThat(AutoLottoTicket().lottoNumbers.size).isEqualTo(LottoTicket.LOTTO_TICKET_SIZE)
    }
}
