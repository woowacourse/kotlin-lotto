package lotto.domain.value

import org.assertj.core.api.Assertions.assertThat
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

    @ParameterizedTest
    @ValueSource(doubles = [0.051, 0.052, 0.053, 0.054])
    fun `소수점 아래 2자리로 반올림한다`(rate: Double) {
        val earningRate = EarningRate(rate)
        assertThat(earningRate.rate).isEqualTo(0.05)
    }
}
