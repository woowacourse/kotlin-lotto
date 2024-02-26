package lotto.model

import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), LottoNumber(6))
        }
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되지 않으면 예외가 발생하지 않는다`() {
        org.junit.jupiter.api.assertDoesNotThrow {
            WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), LottoNumber(7))
        }
    }
}
