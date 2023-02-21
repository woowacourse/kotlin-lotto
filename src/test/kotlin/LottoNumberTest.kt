import domain.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {
    @Test
    fun `로또 숫자는 1에서 45 사이 이외의 숫자로 생성될 수 없다`() {
        assertThrows<IllegalArgumentException> { LottoNumber.from(46) }
    }
}
