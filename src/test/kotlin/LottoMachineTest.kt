import model.LottoMachine
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LottoMachineTest {
    @Test
    fun `6개의 로또 번호를 생성한다`() {
        assertDoesNotThrow {
            val lottoMachine = LottoMachine()
            lottoMachine.generateLotto()
        }
    }
}
