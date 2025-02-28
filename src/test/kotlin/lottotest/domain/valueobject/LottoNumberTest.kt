package lottotest.domain.valueobject

import lotto.domain.valueobject.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 10, 20, 30, 40, 45])
    fun `로또 번호는 1~45 사이의 숫자로 구성된다`(number: Int) {
        // given when
        val lottoNumber = LottoNumber(number)
        // then
        assertThat(lottoNumber.number).isEqualTo(number)
    }
}
