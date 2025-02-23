package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `로또 번호는 1 부터 45 사이가 아니면 예외가 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(value)
        }.apply { assertThat(this).hasMessage("[ERROR] 로또 번호는 1부터 45 사이입니다.") }
    }
}
