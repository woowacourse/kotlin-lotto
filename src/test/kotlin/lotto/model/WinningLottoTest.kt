package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `당첨 번호에 보너스 볼과 중복된 번호가 있는 경우 false를 반환한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = 6
        assertThat(
            WinningLotto(lotto, bonus).hasNoDuplicateNumber()
        ).isFalse
    }
}
