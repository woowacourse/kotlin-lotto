package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NumberOfManualTest {
    @ParameterizedTest
    @CsvSource("1000:2", "3000:5", "10000:11", delimiter = ':')
    fun `수동으로 발행할 로또의 개수는 구매한 로또 개수를 넘길 수 없다`(
        purchaseAmounts: Int,
        manualCounts: Int,
    ) {
        val ticketCounts = NumberOfTickets(purchaseAmounts).counts
        assertThrows<IllegalArgumentException> { NumberOfManual(manualCounts, ticketCounts) }
    }
}
