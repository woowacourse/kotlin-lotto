package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
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
                LottoNumber(6),
            )
        assertDoesNotThrow { Lotto(lottoNumbers) }
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
        assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬되어야 한다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(2),
                LottoNumber(1),
                LottoNumber(4),
                LottoNumber(3),
                LottoNumber(5),
                LottoNumber(6),
            )
        assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
    }
}
