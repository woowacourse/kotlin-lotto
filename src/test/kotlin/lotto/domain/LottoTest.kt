package lotto.domain

import lotto.domain.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `6개의 로또 번호를 갖는다`() {
        assertDoesNotThrow {
            Lotto.createManual((1..6).toSet())
        }
    }

    @Test
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto.createManual((1..5).toSet())
        }
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬되어 반환한다`() {
        // Given
        val lotto = Lotto.createManual(setOf(4, 5, 6, 1, 2, 3))

        // When
        val lottoNumbers = lotto.getLottoNumbers()

        // Then
        assertThat(lottoNumbers.map { it.number }).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
    }
}
