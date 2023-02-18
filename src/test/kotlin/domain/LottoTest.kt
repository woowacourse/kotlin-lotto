package domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LottoTest {
    @Test
    fun `로또 번호가 6개가 아니라면 에러 발생`() {
        // given
        val lottoNumbers = setOf(
            LottoNumber.create(1),
            LottoNumber.create(2),
            LottoNumber.create(3),
            LottoNumber.create(4),
            LottoNumber.create(5)
        )

        // when

        // then
        assertThatIllegalArgumentException().isThrownBy { Lotto(lottoNumbers) }
            .withMessageContaining("${Lotto.PREFIX} 로또번호는 6개여야합니다.")
    }

    @Test
    fun `로또 번호는 6개를 가지고 있어야한다`() {
        // given
        val lottoNumbers = setOf(
            LottoNumber.create(1),
            LottoNumber.create(2),
            LottoNumber.create(3),
            LottoNumber.create(4),
            LottoNumber.create(5),
            LottoNumber.create(6)
        )

        // when

        // then
        assertDoesNotThrow { Lotto(lottoNumbers) }
    }
}
