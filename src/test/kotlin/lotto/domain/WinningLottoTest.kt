package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `보너스 번호는 1부터 45 사이의 숫자여야 한다`() {
        assertThrows<IllegalArgumentException> { WinningLotto(Lotto(1, 2, 3, 4, 5, 6), 0) }
        assertThrows<IllegalArgumentException> { WinningLotto(Lotto(1, 2, 3, 4, 5, 6), 46) }
    }

    @Test
    fun `보너스 번호는 당첨 번호와 달라야 한다`() {
        assertThrows<IllegalArgumentException> { WinningLotto(Lotto(1, 2, 3, 4, 5, 6), 6) }
    }
}
