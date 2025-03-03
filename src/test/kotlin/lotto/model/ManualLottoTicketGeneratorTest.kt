package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoTicketGeneratorTest {
    @Test
    fun `숫자들이 주어지면 로또 티켓이 생성되는 지 확인`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val lottoTicket = ManualLottoTicketGenerator(numbers).generateLottoTicket()
        assertThat(numbers).isEqualTo(lottoTicket.getNumbers())
    }
}
