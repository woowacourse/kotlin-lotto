package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 발행기는 총 로또 구매 개수만큼 로또 티켓을 생성한다`() {
        val autoLottoTicketCount = LottoTicketCount(1)
        val manualLottoNumbers = emptyList<List<Int>>()
        val lottoTickets = LottoMachine().issueLottoTickets(autoLottoTicketCount, manualLottoNumbers)

        val actual = lottoTickets.size

        val expected = 1

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}