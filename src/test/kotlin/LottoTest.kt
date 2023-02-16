import domain.model.LottoResult
import domain.model.WinningNumbers
import domain.model.lotto.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호는 1 이상 45 이하가 아닌 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(setOf(1, 2, 3, 4, 5, 50))
        }
    }

    @Test
    fun `로또 번호는 1 이상 45 이하인 경우`() {
        assertDoesNotThrow {
            Lotto(setOf(1, 45, 10, 11, 12, 13))
        }
    }

    @Test
    fun `로또 번호는 6개가 아닌 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(setOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    @Test
    fun `로또 번호가 6개인 경우`() {
        assertDoesNotThrow {
            Lotto(setOf(1, 45, 10, 11, 12, 13))
        }
    }

    @Test
    fun `당첨 결과가 1등인 경우`() {
        assertThat(
            Lotto(setOf(1, 2, 3, 4, 5, 6)).getLottoResult(
                WinningNumbers(setOf(1, 2, 3, 4, 5, 6), 7)
            )
        ).isEqualTo(
            LottoResult.FIRST
        )
    }

    @Test
    fun `당첨 결과가 2등인 경우`() {
        assertThat(
            Lotto(setOf(1, 2, 3, 4, 5, 6)).getLottoResult(
                WinningNumbers(setOf(1, 2, 3, 4, 5, 10), 6)
            )
        ).isEqualTo(
            LottoResult.SECOND
        )
    }

    @Test
    fun `당첨 결과가 3등인 경우`() {
        assertThat(
            Lotto(setOf(1, 2, 3, 4, 5, 6)).getLottoResult(
                WinningNumbers(setOf(1, 2, 3, 4, 5, 10), 9)
            )
        ).isEqualTo(
            LottoResult.THIRD
        )
    }

    @Test
    fun `당첨되지 않은 경우`() {
        assertThat(
            Lotto(setOf(1, 2, 3, 4, 5, 6)).getLottoResult(
                WinningNumbers(setOf(1, 2, 8, 9, 10, 11), 6)
            )
        ).isEqualTo(
            LottoResult.MISS
        )
    }
}
