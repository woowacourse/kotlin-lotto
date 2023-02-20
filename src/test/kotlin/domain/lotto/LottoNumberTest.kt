package domain.lotto

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import util.PREFIX

class LottoNumberTest {
    @ValueSource(ints = [-1, 0, 46, 47])
    @ParameterizedTest
    fun `로또 번호가 1~45 밖에 있으면 에러 발생`(number: Int) {
        // given

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { LottoNumber.of(number) }
            .withMessageContaining("$PREFIX 로또 넘버는 1~45여야합니다.")
    }

    @ValueSource(ints = [1, 2, 44, 45])
    @ParameterizedTest
    fun `로또 번호는 1~45 안에 있어야한다`(number: Int) {
        // given

        // when
        val lottoNumber: LottoNumber = LottoNumber.of(number)

        // then
        assertDoesNotThrow { lottoNumber }
    }
}
