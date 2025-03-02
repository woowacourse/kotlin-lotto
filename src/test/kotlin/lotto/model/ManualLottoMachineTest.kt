package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ManualLottoMachineTest {
    @Test
    fun `수동 로또 머신에서 주어진 번호 리스트대로 로또를 반환한다`() {
        // given
        val manualLottoMachine = ManualLottoMachine()
        val manualTickets =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(13, 14, 15, 16, 17, 18),
            )

        // when
        val generatedLottos = manualLottoMachine.generate(manualTicket = manualTickets)

        // then
        assertEquals(manualTickets.size, generatedLottos.size)
        manualTickets.forEachIndexed { index, expectedNumbers ->
            val actualNumbers = generatedLottos[index].numbers.map { it.value }
            assertEquals(expectedNumbers, actualNumbers)
        }
    }
}
