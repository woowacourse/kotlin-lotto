import domain.Lotto
import domain.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {

    private val lotto = Lotto(
        listOf(
            LottoNumber(1),
            LottoNumber(2),
            LottoNumber(3),
            LottoNumber(4),
            LottoNumber(5),
            LottoNumber(6)
        )
    )
    @Test
    fun `로또가 6개의 숫자로 잘 생성되었는지 확인`() {
        assertThat(lotto.numbers!!.map { it.number }).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
    }

    @Test
    fun `로또 당첨번호가 null 일 때`() {
        assertThrows<IllegalArgumentException> { Lotto(null) }
    }

    @Test
    fun `길이가 6이 아닌 경우`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                    LottoNumber(7)
                )
            )
        }
    }

    @Test
    fun `중복된 번호가 존재하는 경우`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(5)
                )
            )
        }
    }
}
