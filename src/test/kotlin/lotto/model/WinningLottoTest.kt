package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `당첨 번호에 보너스 볼과 중복된 번호가 있는 경우 생성 시 오류가 발생한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = 6
        assertThrows<IllegalArgumentException> { WinningLotto(lotto, bonus) }
    }

    @Test
    fun `보너스 볼이 범위를 벗어난 경우 생성 시 오류가 발생한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = 77
        assertThrows<IllegalArgumentException> { WinningLotto(lotto, bonus) }
    }
}
