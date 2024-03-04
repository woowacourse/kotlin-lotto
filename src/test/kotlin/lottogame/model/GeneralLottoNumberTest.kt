package lottogame.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GeneralLottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 45])
    fun `로또 번호가 허용 범위 이내에 존재할 때 생성된다`(number: Int) {
        assertThat(GeneralLottoNumber(number).number).isEqualTo(number)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `로또 번호가 허용 범위 이내에 존재하지 않을 때, 예외를 발생시킴`(number: Int) {
        assertThrows<IllegalArgumentException> {
            GeneralLottoNumber(number)
        }
    }
}
