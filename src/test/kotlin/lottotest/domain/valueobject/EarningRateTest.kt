package lottotest.domain.valueobject

import lotto.domain.valueobject.EarningRate
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EarningRateTest {
    @ParameterizedTest
    @ValueSource(doubles = [-0.01, -1.0])
    fun `수익률은 음수일 수 없다`(rate: Double) {
        // when then
        assertThrows<IllegalArgumentException> {
            EarningRate(rate)
        }
    }
}
