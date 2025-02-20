package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 1매에는 6개의 로또 번호가 존재한다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
            )
        val exception = assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
        assertThat(exception.message).isEqualTo("[ERROR] 로또 번호는 6개여야 합니다.")
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(1),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val exception = assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
        assertThat(exception.message).isEqualTo("[ERROR] 로또 번호는 중복될 수 없습니다.")
    }
}
