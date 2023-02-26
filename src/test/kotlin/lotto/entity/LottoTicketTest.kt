package lotto.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTicketTest {

    @Test
    fun `로또 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoTicket(listOf(1, 1, 2, 3, 4, 5)) }
    }

    @Test
    fun `로또 번호가 5개면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoTicket(listOf(1, 2, 3, 4, 5)) }
    }

    @Test
    fun `로또 번호가 7개면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoTicket(listOf(1, 2, 3, 4, 5, 6, 7)) }
    }

    @Test
    fun `1부터 45 사이의 수가 아닌 번호가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoTicket(listOf(1, 2, 3, 4, 5, 46)) }
    }
}
