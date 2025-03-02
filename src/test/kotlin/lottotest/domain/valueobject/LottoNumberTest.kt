package lottotest.domain.valueobject

import lotto.domain.valueobject.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `허용 로또 번호 범위 이내 값이 주여지면 인스턴스를 생성하지 않는다`(value: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(value)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 20, 45])
    fun `허용 로또 번호 범위 이내 값으로 로또 번호를 생성하면 인스턴스에 입력한 값을 포함한다`(value: Int) {
        // given when
        val lottoNumber = LottoNumber(value)

        // then
        assertThat(lottoNumber.value).isEqualTo(value)
    }
}
