package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 46, 100])
    fun `로또 번호는 1부터 45사이의 자연수로 구성이 된다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber.of(number)
        }
    }

    @Test
    fun `캐싱된 LottoNumber을 반환하므로, 숫자가 같은 LottoNumber은 동등하다`() {
        val lottoNumber = LottoNumber.of(5)
        val lottoNumber2 = LottoNumber.of(5)
        assertThat(lottoNumber == lottoNumber2).isTrue()
    }
}
