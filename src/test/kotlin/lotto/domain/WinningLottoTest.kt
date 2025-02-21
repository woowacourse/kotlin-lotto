package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `보너스 번호는 당첨 번호와 중복되지 않는다`() {
        val bonusNumber = LottoNumber(1)
        val winningNumber = Lotto(intToLottoNumber(listOf(1, 2, 3, 4, 5, 6)))

        assertThrows<IllegalArgumentException> {
            WinningLotto(winningNumber, bonusNumber)
        }
    }

    private fun intToLottoNumber(numbers: List<Int>): List<LottoNumber> {
        return numbers.map { LottoNumber(it) }
    }
}
