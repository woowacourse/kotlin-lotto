package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 머신에서 5장을 요청하면 5장의 로또를 반환한다`() {
        val lottoMachine = LottoMachine()

        val expectedQuantity = 5
        val actualQuantity = lottoMachine.getLottos(expectedQuantity).lottos.size

        assertEquals(expectedQuantity, actualQuantity)
    }
}
