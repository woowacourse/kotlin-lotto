package lottotest.domain.valueobject

import lotto.domain.valueobject.EarningRate
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EarningRateTest {
    @ParameterizedTest
    @ValueSource(doubles = [-0.01, -1.0])
    fun `음수인 수익률로 초기화하면 인스턴스를 생성하지 않는다`(rate: Double) {
        // when then
        assertThrows<IllegalArgumentException> {
            EarningRate(rate)
        }
    }
}
