package lotto.domain.model

import lotto.domain.value.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `6개의 로또 번호를 갖는다`() {
        assertDoesNotThrow {
            val lottoNumbers = (1..6).map { LottoNumber(it) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoNumbers = (1..5).map { LottoNumber(it) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        assertDoesNotThrow {
            val lottoNumbers = (1..6).map { LottoNumber(it) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoNumbers = (1..6).map { LottoNumber(1) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬되어 있다`() {
        assertDoesNotThrow {
            val lottoNumbers = (1..6).map { LottoNumber(it) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호가 오름차순으로 정렬되어 있지 않으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoNumbers = (1..6).map { LottoNumber(it) }.reversed()
            Lotto(lottoNumbers)
        }
    }
}
