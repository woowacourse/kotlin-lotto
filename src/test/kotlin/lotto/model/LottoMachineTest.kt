package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoMachineTest {
    @ParameterizedTest
    @CsvSource("1000, 1", "2000, 2", "3000, 3", "10000, 10")
    fun `로또 발행 개수를 계산한다`(
        input: Int,
        expected: Int,
    ) {
        val actual = LottoMachine.getNumberOfTicket(input)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource("1", "2", "3", "10")
    fun `발행 개수 만큼 로또를 발행한다`(count: Int) {
        val result = LottoMachine.issueTickets(count)
        assertThat(result.size).isEqualTo(count)
    }

    @ParameterizedTest
    @CsvSource("1000:2", "3000:5", "10000:11", delimiter = ':')
    fun `수동으로 발행할 로또의 개수는 구매한 로또 개수를 넘길 수 없다`(
        purchaseAmounts: Int,
        manualCounts: Int,
    ) {
        val numberOfTickets = LottoMachine.getNumberOfTicket(purchaseAmounts)
        assertThrows<IllegalArgumentException> { LottoMachine.getNumberOfManual(manualCounts, numberOfTickets) }
    }
}
