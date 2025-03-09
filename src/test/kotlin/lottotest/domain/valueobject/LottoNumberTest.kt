package lottotest.domain.valueobject

import lotto.domain.valueobject.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `로또 번호는 로또 번호 범위 이외의 값을 가질 수 없다`(value: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(value)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 20, 45])
    fun `로또 번호는 로또 번호 범위 이내의 값을 갖는다`(value: Int) {
        // given when
        val lottoNumber = LottoNumber(value)

        // then
        assertThat(lottoNumber.value).isEqualTo(value)
    }
}
