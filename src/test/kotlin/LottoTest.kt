import lotto.model.Lotto
import net.bytebuddy.asm.Advice.OffsetMapping.Factory.Illegal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoTest {
    @Test
    fun `로또 번호 클래스`() {
        assertThat(Lotto(setOf(1, 2, 3, 4, 5, 6)).getNumbers()).isEqualTo((setOf(1, 2, 3, 4, 5, 6)))
    }
    @Test
    fun `로또 번호 개수는 6개 초과`() {
        assertThrows<IllegalArgumentException>{
            Lotto(setOf(1,2,3,4,5,6,7))
        }
    }
    @Test
    fun `로또 번호 개수는 6개 미만`() {
        assertThrows<IllegalArgumentException>{
            Lotto(setOf(1,2,3,4,5))
        }
    }
    @Test
    fun `로또 번호 1~45 사이`(){
        assertThrows<IllegalArgumentException>{
            Lotto(setOf(0,2,3,4,5,46))
        }
    }
}