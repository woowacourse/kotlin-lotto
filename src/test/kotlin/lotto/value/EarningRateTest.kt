package lotto.value

import lotto.domain.value.EarningRate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
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
    @CsvSource(
        "0.000001, 0.0",
        "99.999999, 100.00",
        "0.5049999, 0.50",
        "0.5050000, 0.51",
    )
    fun `수익률은 소수점 3번째 자리에서 반올림된다`(
        rate: Double,
        expected: Double,
    ) {
        val earningRate = EarningRate(rate)
        assertThat(earningRate.rate).isEqualTo(expected)
    }
}
