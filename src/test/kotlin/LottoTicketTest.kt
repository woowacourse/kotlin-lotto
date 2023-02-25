import domain.model.lotto.LottoTicket
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTicketTest {

    @Test
    fun `중복된 숫자가 있는 경우 예외가 발생한다`() {
        val errorMessage = assertThrows<IllegalArgumentException> {
            LottoTicket(listOf(1, 2, 2, 3, 4, 5))
        }
        assertThat(errorMessage.message).isEqualTo(
            "[ERROR] 중복된 숫자가 존재합니다."
        )
    }

    @Test
    fun `숫자 7개인 경우 예외가 발생한다`() {
        val errorMessage = assertThrows<IllegalArgumentException> {
            LottoTicket(listOf(1, 2, 3, 4, 5, 6, 7))
        }
        assertThat(errorMessage.message).isEqualTo(
            "[ERROR] 로또 번호는 6개의 숫자로 이루어져야 합니다."
        )
    }

    @Test
    fun `중복된 숫자가 없고, 숫자가 6개인 경우 예외가 발생하지 않는다`() {
        assertDoesNotThrow { LottoTicket(listOf(1, 2, 3, 4, 5, 6)) }
    }
}
