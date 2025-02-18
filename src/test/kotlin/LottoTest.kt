import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class LottoTest {
    @Test
    fun `로또 번호는 1~45 사이이다`() {
        val lotto = Lotto()
        assertThat(lotto.number).isIn(1..45)
    }
}