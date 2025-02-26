package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또가 6개가 아닌 번호를 가질 때 오류를 반환한다`() {
        val numbers = makeTestLotto(setOf(1, 2, 3, 4, 5))
        assertThrows<IllegalArgumentException> { Lotto(numbers) }
    }

    @Test
    fun `로또번호에서 중복되는 번호가 있을 경우 오류를 반환한다`() {
        val lottoNumbers = makeTestLotto(setOf(1, 1, 2, 3, 4, 5))
        assertThrows<IllegalArgumentException> {
            Lotto(lottoNumbers)
        }
    }

    companion object {
        fun makeTestLotto(lottoNumbers: Set<Int>): Set<LottoNumber> = lottoNumbers.map { LottoNumber(it) }.toSet()
    }
}
