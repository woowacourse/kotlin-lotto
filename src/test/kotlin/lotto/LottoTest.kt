package lotto

import lotto.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `발급하는 로또 번호는 6개여야 한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThat(lotto.numbers.size).isEqualTo(6)
    }

    @Test
    fun `발급하는 로또 번호는 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 5, 6, 7)) }
    }

    @Test
    fun `발급하는 로또 번호는 1부터 45 범위 내에 있어야 한다`() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 5, 46)) }
    }

    @Test
    fun `로또 번호는 중복되지 않아야 한다`() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 6, 6)) }
    }
}
