package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutoLottoCountCalculatorTest {
    private val autoLottoCountCalculator = AutoLottoCountCalculator(3000, 1)

    @Test
    fun `전체 구매금액과 수동로또 개수를 가지고 자동로또 개수를 구한다`() {
        assertThat(autoLottoCountCalculator.calculate()).isEqualTo(2)
    }
}
