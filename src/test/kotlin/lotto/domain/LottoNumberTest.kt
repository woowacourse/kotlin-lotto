package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LottoNumberTest {
    @Test
    internal fun `로또 숫자 조회`() {
        val lottoNumber: LottoNumber = LottoNumber.of(1)

        assertThat(lottoNumber.number).isEqualTo(1)
    }
}
