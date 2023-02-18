package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {

    @ValueSource(ints = [1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `create를 사용하여 인자로 들어간 int값으로 LottoNumber를 만든다`(input: Int) {
        // given

        // when
        val lottoNumber = LottoNumber.create(input)

        // then
        assertThat(lottoNumber.number).isEqualTo(input)
    }

    @ValueSource(ints = [-1, 0, 46, 47])
    @ParameterizedTest
    fun `로또 번호가 1~45 밖에 있으면 에러 발생`(number: Int) {
        // given

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { LottoNumber.create(number) }
            .withMessageContaining("$PREFIX 로또 넘버는 1~45여야합니다.")
    }

    companion object {
        private const val PREFIX = "[Error]"
    }
}
