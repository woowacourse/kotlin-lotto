package domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `개수가 6개면 로또 생성`() {
        val lotto = Lotto(setOf(1,2,3,4,5,6))
        assertThat(lotto.numbers.size).isEqualTo(6)
    }

    @Test
    fun `개수가 6개가 아니라면 exception 발생`() {
        assertThrows<IllegalArgumentException> { Lotto(setOf(1,2,3,4,5)) }
    }

    @Test
    fun `번호가 범위안에 들어오지 않으면 exception 발생`() {
        assertThrows<IllegalArgumentException> { Lotto(setOf(1,2,3,4,5,47)) }
    }
}