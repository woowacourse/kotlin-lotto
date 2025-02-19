import domain.LottoMachine
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    @ParameterizedTest
    @ValueSource(ints = [1000, 2000, 3000, 4000, 5000])
    fun `구입 금액은 1000원 단위이다`(purchaseAmount: Int) {
        assertDoesNotThrow {
            LottoMachine(purchaseAmount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1100, 2100, 3100, 4100, 5100])
    fun `구입 금액은 1000원 단위가 아니면 예외가 발생한다`(purchaseAmount: Int) {
        assertThrows<IllegalArgumentException> {
            LottoMachine(purchaseAmount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 2000, 3000, 4000, 5000])
    fun `구입 금액은 1000원 이상이다`(purchaseAmount: Int) {
        assertDoesNotThrow {
            LottoMachine(purchaseAmount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1000, 100, 200, 300, 400, 500])
    fun `구입 금액은 1000원 이상이 아니면 예외가 발생한다`(purchaseAmount: Int) {
        assertThrows<IllegalArgumentException> {
            LottoMachine(purchaseAmount)
        }
    }

    @Test
    fun `6개의 로또 번호를 생성한다`() {
        assertDoesNotThrow {
            val lottoMachine = LottoMachine(1000)
            lottoMachine.generateLotto()
        }
    }
}
