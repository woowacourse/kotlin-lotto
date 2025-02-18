package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또 번호는 1~45 사이이다`() {
        val lotto = Lotto()
        assertThat(lotto.numbers.all { it in 1..45 }).isTrue()
    }

    @Test
    fun `로또 번호는 중복되지 않는 6개의 숫자를 갖는다`() {
        val lotto = Lotto()
        assertThat(lotto.numbers.size).isEqualTo(6)
    }

    @Test
    fun `로또 1장의 가격은 1000원이다`() {
        val lotto = Lotto()
        assertThat(lotto.price).isEqualTo(1_000)
    }
}
