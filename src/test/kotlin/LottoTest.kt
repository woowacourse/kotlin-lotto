import domain.Lotto
import domain.value.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `6개의 로또 번호를 갖는다`() {
        assertDoesNotThrow {
            val lottoNumbers = (1..6).map { LottoNumber(it) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoNumbers = (1..5).map { LottoNumber(it) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        assertDoesNotThrow {
            val lottoNumbers = (1..6).map { LottoNumber(it) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoNumbers = (1..6).map { LottoNumber(1) }
            Lotto(lottoNumbers)
        }
    }
}
