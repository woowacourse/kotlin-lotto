package domain.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `로또 번호는 1이상 45 이하여야한다`(value: Int) {
        Assertions
            .assertThatThrownBy {
                LottoNumber(value)
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("[ERROR] 로또 번호는 1부터 45 사이입니다.")
    }
}
