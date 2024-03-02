package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutoLottoMachineTest {
    @Test
    fun `입력받은 개수만큼 티켓을 만든다`() {
        val count = 3
        val autoLottoMachine = AutoLottoMachine(count)
        val lottoTickets = autoLottoMachine.make()
        assertThat(lottoTickets.size == count)
    }
}
