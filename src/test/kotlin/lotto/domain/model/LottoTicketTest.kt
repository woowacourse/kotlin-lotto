package lotto.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTicketTest {
    private fun lotto(vararg numbers: Int): Set<LottoNumber> = numbers.map { LottoNumber(it) }.toSet()

    @Test
    fun `로또 번호가 서로 중복되면 예외를 발생시킨다`() {
        val lottoNumbers =
            lotto(1, 2, 3, 4, 5, 5)

        assertThrows<IllegalArgumentException> {
            LottoTicket(lottoNumbers)
        }
    }
}
