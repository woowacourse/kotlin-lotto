package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `6개의 로또 번호를 갖는다`() {
        assertDoesNotThrow {
            val lottoNumbers = (1..6).map { LottoNumber(it) }.toSet()
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoNumbers = (1..5).map { LottoNumber(it) }.toSet()
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬되어 반환한다`() {
        val lottoNumbers = listOf(4, 5, 6, 1, 2, 3).map { LottoNumber(it) }.toSet()
        val lotto = Lotto(lottoNumbers)

        assertThat(lotto.getLottoNumbers().map { it.number }).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
    }
}
