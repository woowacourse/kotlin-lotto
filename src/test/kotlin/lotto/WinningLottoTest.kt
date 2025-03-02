package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.WinningLotto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                WinningLotto(
                    Lotto(setOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }.toSet()),
                    LottoNumber.from(6),
                )
            }
        assertEquals("당첨 번호와 보너스 번호는 중복될 수 없습니다.", exception.message)
    }

    @Test
    fun `당첨 번호를 정상적으로 생성한다`() {
        val winningLotto =
            WinningLotto(
                Lotto(setOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }.toSet()),
                LottoNumber.from(7),
            )
        assertTrue(winningLotto.contains(LottoNumber.from(1)))
        assertFalse(winningLotto.contains(LottoNumber.from(7)))
    }

    @Test
    fun `보너스 번호와 일치하는지 확인한다`() {
        val winningLotto =
            WinningLotto(
                Lotto(setOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }.toSet()),
                LottoNumber.from(7),
            )
        val lotto = Lotto(setOf(1, 2, 3, 4, 5, 7).map { LottoNumber.from(it) }.toSet())
        assertTrue(winningLotto.isBonusMatch(lotto))
    }
}
