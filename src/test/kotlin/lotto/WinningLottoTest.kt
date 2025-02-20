package lotto

import lotto.model.Lotto
import lotto.model.WinningLotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `당첨번호가 중복되면 예외가 발생한다 `() {
        assertThrows<IllegalArgumentException> { WinningLotto(Lotto(listOf(1, 2, 3, 4, 6, 6)), 7) }
    }

    @Test
    fun `당첨번호가 6개가 아니면 예외가 발생한다 `() {
        assertThrows<IllegalArgumentException> { WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6, 7)), 8) }
    }

    @Test
    fun `당첨 번호는 1부터 45 범위 내에 있지 않으면 예외가 발생한다 `() {
        assertThrows<IllegalArgumentException> { WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 46)), 6) }
    }

    @Test
    fun `당첨 번호는 보너스 번호와 중복되면 예외가 발생한다 `() {
        assertThrows<IllegalArgumentException> { WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), 1) }
    }
}
