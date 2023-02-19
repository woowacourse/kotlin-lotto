package domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ValueSource(ints = [1, 45])
    @ParameterizedTest
    fun `1부터 45까지 번호를 받아 생성할 수 있다`(number: Int) {
        assertDoesNotThrow { LottoNumber.valueOf(number) }
    }

    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `1부터 45까지 이외에 숫자를 받으면 에러가 발생한다`(number: Int) {
        assertThatIllegalArgumentException().isThrownBy { LottoNumber.valueOf(number) }
            .withMessage("로또 숫자는 1부터 45사이어야합니다. \n잘못된 값: $number")
    }
}
