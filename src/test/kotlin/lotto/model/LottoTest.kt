package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또는 한 장에 로또 번호 6개를 가진다`() {
        val lotto = LottoTicket()
        assertEquals(lotto.getSize(), 6)
    }

    @Test
    fun `로또 번호가 서로 중복되면 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(5),
                ),
            )
        }
    }
}
