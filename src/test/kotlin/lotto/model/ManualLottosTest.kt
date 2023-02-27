package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualLottosTest {
    @Test
    fun `총 로또 개수보다 많은 수동 로또 개수를 받는 경우 오류를 발생시킨다`() {
        val totalNumber = 5
        val numberOfManualLotto = 6
        assertThrows<IllegalArgumentException> {
            ManualLottos(numberOfManualLotto).validateNumberOfLotto(totalNumber)
        }
    }
}
