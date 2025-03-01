package lotto.domain.model

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ValueSource(ints = [0, 46, 1252451245, -34])
    @ParameterizedTest
    fun `로또의 번호는 1~45이하의 숫자를 가진다`(number: Int) {
        val lottoNumberResult = LottoNumber.from(number)
        assertThatThrownBy { lottoNumberResult.getSuccessOrThrow() }.isInstanceOf(
            IllegalArgumentException::class.java,
        )
    }
}
