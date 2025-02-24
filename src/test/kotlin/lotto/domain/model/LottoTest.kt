package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또를 생성할 수 있다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        assertDoesNotThrow { Lotto(numbers) }
    }

    @Test
    fun `로또는 로또 번호의 개수가 6이 아니라면 예외가 발생한다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
        val exception =
            assertThrows<IllegalArgumentException> {
                Lotto(numbers)
            }

        assertThat(exception.message).isEqualTo("로또 번호는 6개여야 하며, 중복될 수 없습니다.")
    }

    @Test
    fun `중복된 번호가 제거된 뒤 로또 번호의 개수가 6이 아니라면 예외가 발생한다`() {
        val numbers = listOf(1, 1, 2, 3, 4, 5)
        val exception =
            assertThrows<IllegalArgumentException> {
                Lotto(numbers)
            }

        assertThat(exception.message).isEqualTo("로또 번호는 6개여야 하며, 중복될 수 없습니다.")
    }
}
