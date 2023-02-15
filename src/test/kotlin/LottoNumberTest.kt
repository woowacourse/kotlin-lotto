import domain.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {

    @Test
    fun `로또 번호가 잘 생성되었는지 확인`() {
        val lottoNumber = LottoNumber(3)
        assertThat(lottoNumber.number).isEqualTo(3)
    }

    @Test
    fun `로또 번호가 1-45 사이에 없는 경우`() {
        assertThrows<IllegalArgumentException> { LottoNumber(46) }
    }
}
