import domain.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {
    @Test
    fun `로또 숫자의 범위를 벗어나는 경우`() {
        assertThrows<IllegalArgumentException> { LottoNumber.from(46) }
    }
}
