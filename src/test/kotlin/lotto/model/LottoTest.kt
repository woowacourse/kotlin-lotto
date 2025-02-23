package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또가 6개가 아닌 번호를 가질 때 오류를 반환한다`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> {
            Lotto.from(numbers)
        }
    }

    @Test
    fun `로또번호에서 중복되는 번호가 있을 경우 오류를 반환한다`() {
        val lottoNumbers = listOf(1, 1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> {
            Lotto.from(lottoNumbers)
        }
    }

    @Test
    fun `로또 숫자가 1 이상 45 이하이고, 6개의 숫자가 있을 경우 로또를 성공적으로 반환한다`() {
        Lotto.from(listOf(1, 2, 3, 4, 5, 6))
    }
}
