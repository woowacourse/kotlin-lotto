package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoMachineTest {
    @ParameterizedTest
    @CsvSource("1", "2", "3", "10")
    fun `발행 개수 만큼 로또를 발행한다`(count: Int) {
        val lottoMachine =
            LottoMachine(
                TicketCounts(
                    NumberOfTickets(count * NumberOfTickets.TICKET_PRICE),
                    NumberOfManual(0),
                ),
            )
        val result = lottoMachine.issueTickets(count)
        assertThat(result.size).isEqualTo(count)
    }
}
