package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 머신에서 5장을 요청하면 5장의 로또를 반환한다`() {
        // given
        val lottoMachine = LottoMachine()
        val expectedQuantity = 5

        // when
        val actualQuantity = lottoMachine.getTotalLottos(emptyList(), expectedQuantity).size

        // then
        assertEquals(expectedQuantity, actualQuantity)
    }

    @Test
    fun `3장의 수동 로또를 입력하고 총 10장을 요청하면 7장의 로또를 반환한다`() {
        // given
        val lottoMachine = LottoMachine()
        val manualLottos = List(3) { Lotto.from(listOf(1, 2, 3, 4, 5, 6)) }
        val expectedQuantity = 7

        // when
        val actualQuantity = lottoMachine.getTotalLottos(manualLottos, expectedQuantity).size

        // then
        assertEquals(expectedQuantity, actualQuantity)
    }

    @Test
    fun `요청한 수동 로또가 정상적으로 포함되어 반환된다`() {
        // given
        val lottoMachine = LottoMachine()
        val manualLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val manualLottos = listOf(manualLotto)

        // when
        val totalLottos = lottoMachine.getTotalLottos(manualLottos, 10)

        // then
        assertTrue(totalLottos.contains(manualLotto))
    }
}
