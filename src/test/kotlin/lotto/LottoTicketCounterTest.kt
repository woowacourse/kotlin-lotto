package lotto

import lotto.model.LottoTicketCounter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTicketCounterTest {
    @Test
    fun `구입 금액에 따른 로또 갯수를 계산한다`() {
        val calculator = LottoTicketCounter(14000.0)
        assertThat(calculator.count()).isEqualTo(14)
    }

    @Test
    fun `구입 금액이 1000원 미만인 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoTicketCounter(800.0) }
    }
}
