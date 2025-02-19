package lotto.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또가 6개가 아닌 번호를 가질 때 오류를 반환한다`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> { Lotto(numbers) }
    }

    @Test
    fun `당첨 번호와 기존 로또가 동시에 보유하고 있는 번호 갯수를 반환한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningNumbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

        Assertions.assertEquals(6, lotto.compareWinningNumbers(winningNumbers))
    }
}
