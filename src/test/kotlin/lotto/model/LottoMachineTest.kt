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
        val result = lottoMachine.issueAutomaticTickets(count)
        assertThat(result.size).isEqualTo(count)
    }

    @ParameterizedTest
    @CsvSource("1", "2", "3")
    fun `수동 발행 개수 만큼 수동으로 로또를 발행한다`(manualCount: Int) {
        val lottoMachine =
            LottoMachine(
                TicketCounts(
                    NumberOfTickets(manualCount * NumberOfTickets.TICKET_PRICE),
                    NumberOfManual(manualCount),
                ),
            )
        val userManualLotto = lottoMachine.issueAutomaticTickets(manualCount)
        val result = lottoMachine.issueTickets(userManualLotto)
        assertThat(result.size).isEqualTo(manualCount)
    }

    @ParameterizedTest
    @CsvSource("2", "5", "10")
    fun `수동 발행 개수가 0개라면 모든 로또를 자동으로 발행한다`(count: Int) {
        val manualCount = 0
        val lottoMachine =
            LottoMachine(
                TicketCounts(
                    NumberOfTickets(count * NumberOfTickets.TICKET_PRICE),
                    NumberOfManual(manualCount),
                ),
            )
        val result = lottoMachine.issueTickets()
        assertThat(result.size).isEqualTo(count)
    }
}
