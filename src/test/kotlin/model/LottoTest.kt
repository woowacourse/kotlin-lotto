package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {

    @Test
    fun `로또 번호가 6개가 아니면 예외를 발생한다`() {
        // given
        val lotto = listOf(
            LottoNumber(1),
            LottoNumber(2),
            LottoNumber(3),
            LottoNumber(4),
            LottoNumber(5),
            LottoNumber(6),
            LottoNumber(7),
        )
        assertThrows<IllegalArgumentException> {
            Lotto(lotto)
        }
    }

    @Test
    fun `로또의 번호가 중복이면 예외를 발생한다`() {
        // given
        val lotto = listOf(
            LottoNumber(2),
            LottoNumber(2),
            LottoNumber(3),
            LottoNumber(4),
            LottoNumber(5),
            LottoNumber(6),
        )

        assertThrows<IllegalArgumentException> { Lotto(lotto) }
    }
}
