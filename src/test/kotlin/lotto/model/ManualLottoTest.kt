package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualLottoTest {
    @Test
    fun `총 로또 개수보다 많은 수동 로또 개수를 받는 경우 오류를 발생시킨다`() {
        val totalNumber = 5
        val numberOfManualLotto = 6
        assertThrows<IllegalArgumentException> {
            ManualLotto(numberOfManualLotto).validateNumberOfLotto(totalNumber)
        }
    }

    @Test
    fun `수동 로또를 받아 저장한다`() {
        val input = Lotto(1, 2, 3, 4, 5, 6)
        val manualLotto = ManualLotto(1)
        manualLotto.add(input)
        assertThat(manualLotto.lotto).isEqualTo(listOf(input))
    }
}
