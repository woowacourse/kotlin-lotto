package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 45])
    fun `로또 번호가 허용 범위 이내에 존재할 때 생성된다`(number: Int) {
        assertThat(LottoNumber(number).number).isEqualTo(number)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `로또 번호가 허용 범위 이내에 존재하지 않을 때, 예외를 발생시킨다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(number)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 5, 16, 23, 36, 42])
    fun `보너스 로또 번호가 우승 로또 번호 중 하나일 때, 예외를 발생시킨다`(bonusNumber: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(bonusNumber, Lotto(1, 5, 16, 23, 36, 42))
        }
    }
}
