import domain.model.LottoResult
import domain.model.WinningNumbers
import domain.model.lotto.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {

    @Test
    fun `로또 번호는 6개가 아닌 경우 예외가 발생한다`() {
        val errorMessage = assertThrows<IllegalArgumentException> {
            Lotto(setOf(1, 2, 3, 4, 5, 6, 7))
        }.message
        assertThat(errorMessage).isEqualTo("[ERROR] 로또 번호는 6개의 숫자로 이루어져야 합니다.")
    }

    @Test
    fun `로또 번호가 6개인 경우`() {
        assertDoesNotThrow {
            Lotto(setOf(1, 45, 10, 11, 12, 13))
        }
    }

    @Test
    fun `로또번호가 6개 일치하는 경우, 결과는 1등이다`() {
        assertThat(
            Lotto(setOf(1, 2, 3, 4, 5, 6)).getLottoResult(
                WinningNumbers(setOf(1, 2, 3, 4, 5, 6), 7)
            )
        ).isEqualTo(
            LottoResult.FIRST
        )
    }

    @Test
    fun `로또번호가 5개와 보너스 번호가 일치하는 경우, 결과는 2등이다`() {
        assertThat(
            Lotto(setOf(1, 2, 3, 4, 5, 6)).getLottoResult(
                WinningNumbers(setOf(1, 2, 3, 4, 5, 10), 6)
            )
        ).isEqualTo(
            LottoResult.SECOND
        )
    }

    @Test
    fun `로또번호가 5개가 일치하고 보너스 번호가 일치하지 않는 경우, 결과는 3등이다`() {
        assertThat(
            Lotto(setOf(1, 2, 3, 4, 5, 6)).getLottoResult(
                WinningNumbers(setOf(1, 2, 3, 4, 5, 10), 9)
            )
        ).isEqualTo(
            LottoResult.THIRD
        )
    }

    @Test
    fun `로또번호가 2개 이하 일치하는 경우, 당첨되지 않는다`() {
        assertThat(
            Lotto(setOf(1, 2, 3, 4, 5, 6)).getLottoResult(
                WinningNumbers(setOf(1, 2, 8, 9, 10, 11), 6)
            )
        ).isEqualTo(
            LottoResult.MISS
        )
    }
}
