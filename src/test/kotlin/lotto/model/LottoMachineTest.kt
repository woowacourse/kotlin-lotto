package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoMachineTest {
    private val userManualLotto =
        listOf(
            "1, 2, 3, 4, 5, 6",
            "2, 3, 4, 5, 6, 7",
            "3, 4, 5, 6, 7, 8",
        )

    @ParameterizedTest
    @CsvSource("1", "2", "3", "10")
    fun `발행 개수 만큼 로또를 발행한다`(count: Int) {
        val lottoMachine = LottoMachine.createWithCounts(count, 0)
        val result = lottoMachine.issueAutomaticTickets(count)
        assertThat(result.size).isEqualTo(count)
    }

    @ParameterizedTest
    @CsvSource("1", "2", "3")
    fun `수동 발행 개수 만큼 수동으로 로또를 발행한다`(manualCount: Int) {
        val lottoMachine = LottoMachine.createWithCounts(manualCount, manualCount)
        val result = lottoMachine.issueManualTickets(userManualLotto.take(manualCount))
        assertThat(result.size).isEqualTo(manualCount)
    }

    @ParameterizedTest
    @CsvSource("1:2", "3:3", "3:6", delimiter = ':')
    fun `수동으로 로또 발행 후 남은 로또 개수만큼 자동으로 발행한다`(
        manualCount: Int,
        totalCount: Int,
    ) {
        val lottoMachine = LottoMachine.createWithCounts(totalCount, manualCount)
        val manual = lottoMachine.issueManualTickets(userManualLotto.take(manualCount))
        val automatic =
            lottoMachine.issueAutomaticTickets(lottoMachine.ticketCounts.getAutomaticTicketCounts())
        assertThat(automatic.size + manual.size).isEqualTo(totalCount)
        assertThat(automatic.size).isEqualTo(totalCount - manualCount)
    }

    @ParameterizedTest
    @CsvSource("2", "5", "10")
    fun `수동 발행 개수가 0개라면 모든 로또를 자동으로 발행한다`(count: Int) {
        val manualCount = 0
        val lottoMachine = LottoMachine.createWithCounts(count, manualCount)
        val result = lottoMachine.issueTickets()
        assertThat(result.size).isEqualTo(count)
    }
}
