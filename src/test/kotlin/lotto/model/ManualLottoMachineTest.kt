package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoMachineTest {
    @Test
    fun `수동로또를 입력받아 로또티켓으로 만든다`() {
        val manualLottoNumbers = listOf(listOf(1, 2, 3, 4, 5, 6), listOf(7, 8, 9, 10, 11, 12))
        val manualLottoMachine = ManualLottoMachine(manualLottoNumbers)
        val manualLottoTickets = manualLottoMachine.makeManualTickets()
        assertThat(manualLottoTickets.size).isEqualTo(2)
    }
}
