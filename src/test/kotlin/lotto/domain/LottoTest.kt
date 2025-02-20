package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `당첨 번호는 총 6개를 입력해야 한다`() {
        assertThrows<IllegalArgumentException> { Lotto(setOf<LottoNumber>()) }
        assertThrows<IllegalArgumentException> { Lotto(setOf(1).map { LottoNumber(it) }.toSet()) }
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2).map { LottoNumber(it) }.toSet()) }
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2, 3).map { LottoNumber(it) }.toSet()) }
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2, 3, 4).map { LottoNumber(it) }.toSet()) }
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2, 3, 4, 5).map { LottoNumber(it) }.toSet()) }
    }

    @Test
    fun `로또 번호가 중복되면 안 된다`() {
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2, 3, 1, 2, 3).map { LottoNumber(it) }.toSet()) }
    }
}
