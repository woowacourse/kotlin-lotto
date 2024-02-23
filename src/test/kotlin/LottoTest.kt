import lotto.model.Lotto
import lotto.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호 클래스`() {
        // 숫자들을 LottoNumber로 변환하여 Lotto 객체 생성
        val lottoNumbers = setOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()
        val expectedNumbers =
            setOf(LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6))
        assertThat(Lotto(lottoNumbers).numbers).isEqualTo(expectedNumbers)
    }

    @Test
    fun `로또 번호 개수는 6개 초과`() {
        // 숫자들을 LottoNumber로 변환하여 Lotto 객체 생성 시도
        val lottoNumbers = setOf(1, 2, 3, 4, 5, 6, 7).map { LottoNumber(it) }.toSet()
        assertThrows<IllegalArgumentException> {
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호 개수는 6개 미만`() {
        // 숫자들을 LottoNumber로 변환하여 Lotto 객체 생성 시도
        val lottoNumbers = setOf(1, 2, 3, 4, 5).map { LottoNumber(it) }.toSet()
        assertThrows<IllegalArgumentException> {
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호 1~45 사이`() {
        assertThrows<IllegalArgumentException> {
            LottoNumber(46)
        }
    }
}
