package lotto.model

import lotto.domain.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {
    @Test
    fun `유효 범위를 벗어난 숫자로 LottoNumber 생성 시 null을 반환한다`() {
        val lottoNumber = LottoNumber.valueOfOrNull(-1)
        assertThat(lottoNumber).isNull()
    }

    @Test
    fun `유효 범위를 벗어난 숫자로 LottoNumber 생성 시 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { LottoNumber.valueOf(-1) }
    }
}
