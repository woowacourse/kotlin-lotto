package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {

    @Test
    fun `당첨번호와 보너스번호가 6으로 중복된다`() {
        // given
        val winningNumber = Lotto(
            listOf<LottoNumber>(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            ),
        )

        val bonusNumber = LottoNumber(6)

        // when, then
        assertThrows<IllegalArgumentException>("[ERROR] 중복된 보너스 번호입니다") {
            WinningNumbers(winningNumber, bonusNumber)
        }
    }
}
