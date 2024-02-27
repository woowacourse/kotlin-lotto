package lotto.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class NumberOfManualTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 3, 10])
    fun `수동으로 발행할 로또의 개수는 0 이상의 정수여야 한다`(manualCounts: Int) {
        assertDoesNotThrow { NumberOfManual(manualCounts) }
    }

    @ParameterizedTest
    @CsvSource("2:1", "5:3", "11:10", delimiter = ':')
    fun `수동으로 발행할 로또의 개수는 구매한 로또 개수를 넘길 수 없다`(
        manualCounts: Int,
        totalCounts: Int,
    ) {
        val numberOfTickets = NumberOfTickets(totalCounts)
        val numberOfManual = NumberOfManual(manualCounts)
        assertThrows<IllegalArgumentException> { TicketCounts(numberOfTickets, numberOfManual) }
    }
}
