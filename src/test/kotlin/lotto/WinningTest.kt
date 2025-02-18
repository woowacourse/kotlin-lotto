package lotto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningTest {
    @Test
    fun `보너스 볼은 당첨 번호와 중복되지 않는다`() {
        val bonusNum: Int = 7
        val lottoNums = listOf(1, 2, 3, 4, 5, 6)
        val winningNums = Lotto(lottoNums)
        assertThrows<IllegalArgumentException> { Winning(winningNums, bonusNum) }
    }
}
