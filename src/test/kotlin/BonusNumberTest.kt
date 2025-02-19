import domain.model.BonusNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BonusNumberTest {
    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `보너스볼 번호는 1이상 45 이하여야한다`(value: Int) {
        Assertions.assertThatThrownBy {
            BonusNumber(value)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("[ERROR] 보너스 볼 번호는 1부터 45 사이입니다.")
    }
}
