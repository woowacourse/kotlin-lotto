package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoMachineTest {
    private val manualLottoMachine = ManualLottoMachine(listOf(listOf(6, 5, 4, 3, 2, 1)))

    @Test
    fun `입력받은 넘버들로 수동로또 티켓을 만든다`() {
        val manualLottoTickets = manualLottoMachine.make()
        assertThat(manualLottoTickets.map { it.userLottoTicket.map { it.number } })
            .isEqualTo(
                listOf(listOf(1, 2, 3, 4, 5, 6)),
            )
    }
}
