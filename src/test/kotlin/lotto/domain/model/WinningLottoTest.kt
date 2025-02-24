package lotto.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    private fun lotto(vararg numbers: Int): Set<LottoNumber> = numbers.map { LottoNumber(it) }.toSet()

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외를 발생시킨다`() {
        val winningNumbers = lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = 6

        assertThrows<IllegalArgumentException> {
            WinningLotto(winningNumbers, LottoNumber(bonusNumber))
        }
    }
}
