package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 머신에서 5장을 요청하면 5장의 로또를 반환한다`() {
        // given
        val lottoMachine = LottoMachine()
        val expectedQuantity = 5

        // when
        val actualQuantity = lottoMachine.getLottos(expectedQuantity).size

        // then
        assertEquals(expectedQuantity, actualQuantity)
    }
}
