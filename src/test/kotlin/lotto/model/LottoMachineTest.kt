package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 999])
    fun `구입 금액은 자연수이면서 1000 이상이다`(price: Int) {
        assertThrows<IllegalArgumentException> {
            LottoMachine(price)
        }
    }
}
