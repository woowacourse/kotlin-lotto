package lotto.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoBundleTest {
    @Test
    fun `로또 번들에 로또가 0개 이하라면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoBundle(listOf())
        }
    }

    @Test
    fun `로또 번들은 로또를 1개 이상 가지고 있어야 한다`() {
        assertDoesNotThrow {
            LottoBundle(List(1) { Lotto(listOf(1, 2, 3, 4, 5, 6)) })
        }
    }
}
