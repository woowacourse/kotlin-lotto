package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningNumberTest {

    @Test
    fun `보너스번호는 당첨번호와 중복되면 안된다`() {
        // given
        val lotto = Lotto(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            ),
        )
        val bonusNumber = LottoNumber(3)
        // when
        assertThrows<IllegalArgumentException> {
            WinningNumber(lotto, bonusNumber)
        }
    }
}
