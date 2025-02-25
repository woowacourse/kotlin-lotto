package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또는 중복되지 않는 6개의 숫자를 갖는다`() {
        val lotto = Lotto(listOf(LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6)).toSet())
        assertThat(lotto.getSortedLotto().size).isEqualTo(6)
    }

    @Test
    fun `로또는 중복된 숫자를 가지면 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(LottoNumber(1), LottoNumber(1), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6)).toSet())
        }
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬된다`() {
        val lotto = Lotto(listOf(LottoNumber(7), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6)).toSet())
        assertThat(
            lotto.getSortedLotto(),
        ).isEqualTo(listOf(LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6), LottoNumber(7)))
    }
}
