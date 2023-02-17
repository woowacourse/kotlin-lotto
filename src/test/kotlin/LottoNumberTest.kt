import domain.model.lotto.LottoNumber
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 45])
    fun `로또 번호가 1 이상 45 이하인 경우`(value: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber.from(value)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `로또 번호는 1 이상 45 이하가 아닌 경우 예외가 발생한다`(value: Int) {
        assertDoesNotThrow {
            LottoNumber.from(value)
        }
    }
}