import domain.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또가 6개의 숫자로 잘 생성되었는지 확인`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThat(lotto.numbers).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
    }

    @Test
    fun `로또 당첨번호가 null 일 때`() {
        assertThrows<IllegalArgumentException> { Lotto(null) }
    }

    @Test
    fun `길이가 6이 아닌 경우`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThat(lotto.numbers?.size).isEqualTo(6)
    }

    @Test
    fun `중복된 번호가 존재하는 경우`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 5))
        }
    }

    @Test
    fun `로또 번호가 1-45 사이의 숫자가 아닌 경우`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 60))
        }
    }
}
