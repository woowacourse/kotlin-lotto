package lotto.model

import lotto.Lotto
import lotto.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 1매에 로또 번호 갯수가 6개가 아니라면 null를 반환한다`() {
        val lotto = createLotto(listOf(1, 2, 3, 4, 5))
        assertThat(lotto).isEqualTo(null)
    }

    @Test
    fun `로또 1매에 로또 번호 갯수가 중복된다면 null를 반환한다`() {
        val lotto = createLotto(listOf(1, 2, 3, 4, 5, 1))
        assertThat(lotto).isEqualTo(null)
    }

    @Test
    fun `로또 1매에 로또 번호가 오름차순이 아니라면 오류를 반환한다`() {
        assertThrows<IllegalArgumentException> { createLotto(listOf(6, 5, 4, 3, 2, 1)) }
    }

    fun createLotto(numberList: List<Int>): Lotto? = Lotto.createOrNull(numberList.map { it -> LottoNumber.valueOf(it) })
}
