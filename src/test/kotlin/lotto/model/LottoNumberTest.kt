package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ValueSource(ints = [1, 45])
    @ParameterizedTest
    fun `로또 번호를 입력하면, 입력한 숫자를 보유한 로또 번호 객체가 반환된다`(number: Int) {
        // given & when
        val lottoNumber = LottoNumber.from(number)

        // then
        assertEquals(number, lottoNumber.number)
    }

    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `로또 번호의 범위가 1 미만 45 초과면 에러를 반환한다`(number: Int) {
        // given & when & then
        assertThrows<IllegalArgumentException> {
            LottoNumber.from(number)
        }
    }
}
