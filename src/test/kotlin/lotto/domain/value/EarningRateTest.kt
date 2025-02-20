package lotto.domain.value

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EarningRateTest {
    @ParameterizedTest
    @ValueSource(doubles = [0.0, 0.1, 1.0, 1000.0])
    fun `수익률은 0 이상의 실수이다`(rate: Double) {
        assertDoesNotThrow {
            EarningRate(rate)
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = [-0.1, -1.0, -1000.0])
    fun `수익률이 0 이상이 아니면 예외가 발생한다`(rate: Double) {
        assertThrows<IllegalArgumentException> {
            EarningRate(rate)
        }
    }
}
