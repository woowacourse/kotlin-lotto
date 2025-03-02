package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AutoLottoMachineTest {
    @Test
    fun `로또 머신에서 5장을 요청하면 5장의 로또를 반환한다`() {
        // given
        val lottoMachine = AutoLottoMachine()
        val expectedQuantity = 5

        // when
        val actualQuantity = lottoMachine.generate(quantity = expectedQuantity).size

        // then
        assertEquals(expectedQuantity, actualQuantity)
    }
}
