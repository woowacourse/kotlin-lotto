package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTicketTest {
    @Test
    fun `자동 생성된 로또번호의 개수는 6이다`() {
        val lotto = LottoTicket()
        assertEquals(lotto.getSize(), 6)
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외를 발생시킨다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val bonusNumber = 6

        assertThrows<IllegalArgumentException> {
            LottoTicket(lottoNumbers, LottoNumber(bonusNumber))
        }
    }

    @Test
    fun `로또 번호가 서로 중복되면 예외를 발생시킨다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(5),
            )

        assertThrows<IllegalArgumentException> {
            LottoTicket(lottoNumbers)
        }
    }
}
