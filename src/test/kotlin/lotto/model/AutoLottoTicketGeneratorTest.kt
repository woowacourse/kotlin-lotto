package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AutoLottoTicketGeneratorTest {
    @Test
    fun `로또 자동 발행하면, 자동 로또 티켓을 반환한다`() {
        val actual = AutoLottoTicketGenerator().generateLottoTicket().lottoIssueType

        val expected = LottoIssueType.AUTO

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}