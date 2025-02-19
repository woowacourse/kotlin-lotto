import domain.value.EarningRate
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class EarningRateTest {
    @ParameterizedTest
    @ValueSource(doubles = [0.0, 0.1, 0.5, 1.0])
    fun `수익률은 0 이상 1 이하인 실수이다`(rate: Double) {
        assertDoesNotThrow {
            EarningRate(rate)
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = [-0.1, 1.1, 2.0])
    fun `수익률이 0 이상 1 이하가 아니면 예외가 발생한다`(rate: Double) {
        assertThrows<IllegalArgumentException> {
            EarningRate(rate)
        }
    }
}
