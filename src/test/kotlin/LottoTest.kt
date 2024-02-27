import lotto.model.GameException
import lotto.model.Lotto
import lotto.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.TreeSet

class LottoTest {
    @Test
    fun `로또 번호 클래스`() {
        assertThat(Lotto(LottoNumber(TreeSet(setOf(1, 2, 3, 4, 5, 6)))).getLottoNumber())
            .isEqualTo((setOf(1, 2, 3, 4, 5, 6)))
    }

    @Test
    fun `로또 번호 개수는 6개 초과`() {
        assertThrows<GameException> {
            Lotto(LottoNumber(TreeSet(setOf(1, 2, 3, 4, 5, 6, 7))))
        }
    }

    @Test
    fun `로또 번호 개수는 6개 미만`() {
        assertThrows<GameException> {
            Lotto(LottoNumber(TreeSet(setOf(1, 2, 3, 4, 5))))
        }
    }

    @Test
    fun `로또 번호 1~45 사이`() {
        assertThrows<GameException> {
            Lotto(LottoNumber(TreeSet(setOf(0, 2, 3, 4, 5, 46))))
        }
    }
}
