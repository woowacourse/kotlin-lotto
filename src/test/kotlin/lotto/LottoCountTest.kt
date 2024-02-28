package lotto

import lotto.model.LottoCount
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoCountTest {
    @Test
    fun `수동 로또 개수가 살 수 있는 로또 개수보다 클 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoCount(1, "9")
        }
    }

    @Test
    fun `수동 로또 개수가 살 수 있는 로또 개수보다 작을 경우 예외가 발생하지 않는다`() {
        assertDoesNotThrow {
            LottoCount(9, "1")
        }
    }

    @Test
    fun `수동 로또 개수가 Int가 아니라면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoCount(9, "a")
        }
    }
}
