package lotto.model

import lotto.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {
    @Test
    fun `로또 넘버를 isEqualTo로 생성할 때, 유효한 범위 외의 값이 들어오면 null를 리턴한다`() {
        val lottoNumber = LottoNumber.createOrNull(-1)
        assertThat(lottoNumber).isEqualTo(null)
    }

    @Test
    fun `로또 넘버를 valueOf로 생성할 때, 유효한 범위 외의 값이 들어오면 throw 한다`() {
        assertThrows<IllegalArgumentException> { LottoNumber.valueOf(-1) }
    }
}
