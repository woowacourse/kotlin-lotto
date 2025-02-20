package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또는 한 장에 로또 번호 6개를 가진다`() {
        val lotto = Lotto()
        assertEquals(lotto.getSize(), 6)
    }

    @Test
    fun `로또 번호는 서로 중복되지 않는다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
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
