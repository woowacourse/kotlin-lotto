package model

import model.Lotto.Companion.ERROR_LOTTO_NUMBER_DUPLICATION
import model.Lotto.Companion.ERROR_LOTTO_SIZE
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {

    @Test
    fun `로또가 7개의 번호를 가지고 있다`() {
        // given
        val lotto = listOf<LottoNumber>(
            LottoNumber(1),
            LottoNumber(2),
            LottoNumber(3),
            LottoNumber(4),
            LottoNumber(5),
            LottoNumber(6),
            LottoNumber(7),
        )

        // when, then
        assertThrows<IllegalArgumentException>(ERROR_LOTTO_SIZE) {
            Lotto(lotto)
        }
    }

    @Test
    fun `로또가 5개이하의 번호를 가지고 있다`() {
        // given
        val lotto = listOf<LottoNumber>(
            LottoNumber(1),
            LottoNumber(2),
            LottoNumber(3),
            LottoNumber(4),
            LottoNumber(5),
        )

        // when, then
        assertThrows<IllegalArgumentException>(ERROR_LOTTO_SIZE) {
            Lotto(lotto)
        }
    }

    @Test
    fun `로또 번호가 2개 중복되어 있다`() {
        // given
        val lotto = listOf<LottoNumber>(
            LottoNumber(1),
            LottoNumber(2),
            LottoNumber(3),
            LottoNumber(4),
            LottoNumber(5),
            LottoNumber(5),
        )

        // when, then
        assertThrows<IllegalArgumentException>(ERROR_LOTTO_NUMBER_DUPLICATION) {
            Lotto(lotto)
        }
    }
}
