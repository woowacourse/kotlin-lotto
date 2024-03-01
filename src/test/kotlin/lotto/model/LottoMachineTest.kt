package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    private val lottoMachine = LottoMachine()

    @Test
    fun `인자로 받은 랜덤넘버를 정렬해서 티켓을 만든다`() {
        val lottoNumbers = listOf(listOf(6, 5, 4, 3, 2, 1))
        val lottoTickets = lottoMachine.make(lottoNumbers)
        lottoTickets.forEach {
            var num = 1
            it.userLottoTicket.forEach {
                assertThat(it.number).isEqualTo(num++)
            }
        }
    }
}
