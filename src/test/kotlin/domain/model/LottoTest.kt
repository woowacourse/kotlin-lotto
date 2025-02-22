package domain.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {
    @ValueSource(strings = ["1,2,3,4,5,6,7", "1,2,3,4", "1,2,3,4,5"])
    @ParameterizedTest
    fun `로또 번호는 6개가 아니면 예외가 발생한다`(value: String) {
        val values = value.split(",").map { LottoNumber(it.toInt()) }
        Assertions
            .assertThatThrownBy {
                Lotto(values)
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("[ERROR] 로또 번호는 6개 입니다.")
    }

    @ValueSource(strings = ["1,2,2,3,4,5", "3,3,3,3,3,3"])
    @ParameterizedTest
    fun `로또 번호에 중복이 있으면 예외가 발생한다`(value: String) {
        val values = value.split(",").map { LottoNumber(it.toInt()) }
        Assertions
            .assertThatThrownBy {
                Lotto(values)
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("[ERROR] 로또 번호는 중복이 없습니다.")
    }
}
