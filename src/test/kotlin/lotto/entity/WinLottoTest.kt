package lotto.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinLottoTest {
    @Test
    fun `보너스 번호와 당첨번호가 중복되면 예외가 발생한다`() {
        val winNumber = Lotto(
            setOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6)
            )
        )
        val bonus = LottoNumber(1)
        assertThrows<IllegalArgumentException> { WinLotto(winNumber, bonus) }
    }
}
