import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또 가격을 설정할 수 있다`() {
        assertThat(Lotto(1000).price).isEqualTo(1000)
    }
}

class Lotto(val price: Int)
