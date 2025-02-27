package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또는 한 장에 로또 번호 6개를 가진다`() {
        val lotto = LottoTicket.create(1, 2, 3, 4, 5, 6)
        assertEquals(lotto.getSize(), 6)
    }

    @Test
    fun `로또 번호는 서로 중복된 수가 주어지면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoTicket.create(1, 2, 3, 4, 5, 5)
        }
    }
}
