import domain.model.lotto.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {
    @Test
    fun `로또 번호는 1 이상 45 이하가 아닌 경우 예외가 발생한다`() {
        val errorMessage = assertThrows<IllegalArgumentException> {
            LottoNumber.from(50)
        }.message
        assertThat(errorMessage).isEqualTo("[ERROR] 번호는 1 이상 45 이하입니다.")
    }

    @Test
    fun `로또 번호가 1 이상 45 이하인 경우`() {
        assertDoesNotThrow {
            LottoNumber.from(5)
        }
    }
}
