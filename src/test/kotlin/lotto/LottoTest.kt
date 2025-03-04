package lotto

import lotto.model.Lotto
import lotto.model.Lotto.Companion.LOTTO_COUNT_MESSAGE
import lotto.model.Lotto.Companion.LOTTO_DISTINCT_MESSAGE
import lotto.model.LottoNumber
import lotto.model.Rank
import lotto.model.WinningLotto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Lotto(setOf(1, 2, 3, 4, 5).map { LottoNumber.from(it) }.toSet())
            }
        assertEquals(LOTTO_COUNT_MESSAGE, exception.message)
    }

    @Test
    fun `로또 번호가 중복되면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Lotto.from(listOf(1, 2, 3, 4, 5, 5))
            }
        assertEquals(LOTTO_DISTINCT_MESSAGE, exception.message)
    }

    @Test
    fun `로또 번호가 1부터 45 사이의 숫자가 아니면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Lotto.from(listOf(1, 2, 3, 4, 5, 46))
            }
        assertEquals("로또 번호는 1에서 45 범위 내에서 있어야 합니다.", exception.message)
    }

    @Test
    fun `로또 번호를 생성한다`() {
        val lotto = Lotto(setOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }.toSet())
        assertEquals(setOf(1, 2, 3, 4, 5, 6), lotto.numbers.map { it.toString().toInt() }.toSet())
    }

    @Test
    fun `로또 번호가 당첨 번호와 일치하는지 확인한다`() {
        val lotto = Lotto(setOf(1, 2, 3, 4, 5, 8).map { LottoNumber.from(it) }.toSet())
        val winningLotto =
            WinningLotto(
                Lotto(setOf(1, 2, 3, 4, 5, 7).map { LottoNumber.from(it) }.toSet()),
                LottoNumber.from(8),
            )
        assertEquals(Rank.SECOND, lotto.match(winningLotto))
    }
}
