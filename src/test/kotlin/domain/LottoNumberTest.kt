package domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @MethodSource("provideValidRangeLottoNumber")
    fun `1이사 45이하 숫자가 주어졌을 때, 로또 객체 생성시, 예외가 발생하지 않는다`(number: Int) {
        val lottoNumber = LottoNumber(number)
        Assertions.assertThat(lottoNumber.value).isBetween(1, 45)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46, 100])
    fun `1미만 45초과 숫자가 주어졌을 때, 로또 객체 생성시, IllegalArgumentException 가 발생한다`() {
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            val lottoNumber = LottoNumber(0)
            Assertions.assertThat(lottoNumber.value).isBetween(1, 45)
        }
    }

    companion object {
        @JvmStatic
        fun provideValidRangeLottoNumber(): List<Arguments> =
            (1..45).map { number ->
                Arguments.of(number)
            }
    }
}
