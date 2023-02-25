package lotto.model

import org.assertj.core.api.Assertions.assertThat
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

    @Test
    fun `입력된 수동 로또를 저장한다`() {
        val result = ManualLottos(2).generateManualLotto { getLottoNumber() }
        assertThat(result.size).isEqualTo(2)
    }

    private fun getLottoNumber(): Lotto {
        return Lotto(1, 2, 3, 4, 5, 6)
    }
}
