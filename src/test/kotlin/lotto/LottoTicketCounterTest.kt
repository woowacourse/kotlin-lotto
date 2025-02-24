package lotto

import lotto.model.LottoTicketCounter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

class LottoTicketCounterTest {
    @Test
    fun `구입 금액에 따른 로또 갯수를 계산한다`() {
        val calculator = LottoTicketCounter("14000")
        assertThat(calculator.count()).isEqualTo(14)
    }

    @EmptySource
    @ParameterizedTest
    fun `구입 금액이 공백이면 예외가 발생한다`(input: String) {
        assertThrows<IllegalArgumentException> { LottoTicketCounter(input) }
    }

    @Test
    fun `구입 금액은 숫자가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoTicketCounter("1000s") }
    }

    @Test
    fun `구입 금액이 1000원 미만인 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoTicketCounter("800") }
    }

    @Test
    fun `구입 금액은 1000으로 나누어 떨어져야 한다`() {
        assertThrows<IllegalArgumentException> { LottoTicketCounter("1300") }
    }
}
