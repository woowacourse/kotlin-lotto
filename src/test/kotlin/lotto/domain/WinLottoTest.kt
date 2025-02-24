package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinLottoTest {
    @Test
    fun `보너스 번호는 당첨 번호와 달라야 한다`() {
        assertThrows<IllegalArgumentException> {
            WinLotto(
                Lotto(setOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()),
                LottoNumber(6),
            )
        }
    }
}
