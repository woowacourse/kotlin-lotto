package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `번호의 개수가 6개면 로또 생성`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        assertThat(lotto.numbers.size).isEqualTo(6)
    }

    @Test
    fun `로또 번호는 1부터 45 사이의 번호로 구성된다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(1, 2, 3, 4, 5)
        }
    }

    @Test
    fun `로또 번호는 45보다 큰 숫자로 생성할 수 없다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(1, 2, 3, 4, 46)
        }
    }

    @Test
    fun `로또 번호는 1보다 작은 숫자로 생성할 수 없다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(1, 2, 3, 4, 0)
        }
    }
}
