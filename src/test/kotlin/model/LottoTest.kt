package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {

    @Test
    fun `로또 번호가 6개가 아니면 예외를 발생한다`() {
        // given
        val lotto = listOf(
            LottoNumber.from(1),
            LottoNumber.from(2),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5),
            LottoNumber.from(6),
            LottoNumber.from(7)
        )
        assertThrows<IllegalArgumentException> {
            Lotto(lotto)
        }
    }

    @Test
    fun `로또의 번호가 중복이면 예외를 발생한다`() {
        // given
        val lotto = listOf(
            LottoNumber.from(2),
            LottoNumber.from(2),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5),
            LottoNumber.from(6),
        )

        assertThrows<IllegalArgumentException> { Lotto(lotto) }
    }
}
