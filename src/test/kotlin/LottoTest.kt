import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {

    @Test
    fun `로또의 번호가 6개인 경우`() {
        val numbers = setOf(
            LottoNumber.from(1),
            LottoNumber.from(2),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5),
            LottoNumber.from(6)
        )

        assertDoesNotThrow {
            Lotto(numbers)
        }
    }

    @Test
    fun `로또의 번호가 6개가 아닌 경우 예외가 발생`() {
        val numbers = setOf(
            LottoNumber.from(1),
            LottoNumber.from(2),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5),
            LottoNumber.from(6),
            LottoNumber.from(7)
        )

        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }
}
