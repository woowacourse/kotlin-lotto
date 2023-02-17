package domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import util.PREFIX

class LottoTest {
    @Test
    fun `로또 번호가 6개가 아니라면 에러 발생`() {
        // given
        val lottoNumbers = setOf(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
        )

        // when

        // then
        assertThatIllegalArgumentException().isThrownBy { Lotto(lottoNumbers) }
            .withMessageContaining("$PREFIX 로또번호는 6개여야합니다.")
    }

    @Test
    fun `로또 번호는 6개를 가지고 있어야한다`() {
        // given
        val lottoNumbers = setOf(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6),
        )

        // when

        // then
        assertDoesNotThrow { Lotto(lottoNumbers) }
    }
}
