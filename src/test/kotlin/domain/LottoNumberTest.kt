package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {

    @ValueSource(ints = [1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `이미 캐싱된 LottoNumber를 가져온다`(input: Int) {
        // given

        // when
        val lottoNumber = LottoNumber.of(input)

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
            .isThrownBy { LottoNumber.of(number) }
            .withMessageContaining("[Error] 로또 넘버는 1~45여야합니다.")
    }

    @ValueSource(strings = ["", "a", " "])
    @ParameterizedTest
    fun `숫자가 아닌 값이 들어올 때 NumberFormatException을 발생시킨다`(number: String) {
        // given

        // when
        val exception = assertThrows<NumberFormatException> { LottoNumber.of(number) }

        // then
        assertThat(exception.message).isEqualTo("[Error] 숫자로 입력해주세요.")
    }
}
