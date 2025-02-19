package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `당첨 번호는 총 6개를 입력해야 한다`() {
        assertThrows<IllegalArgumentException> { Lotto() }
        assertThrows<IllegalArgumentException> { Lotto(1) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4, 5) }
    }

    @Test
    fun `당첨 번호는 1부터 45 사이의 숫자여야 한다`() {
        assertThrows<IllegalArgumentException> { Lotto(0, 1, 2, 3, 4, 5) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4, 5, 46) }
    }

    @Test
    fun `로또 번호가 중복되면 안 된다`() {
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 1, 2, 3) }
    }
}
