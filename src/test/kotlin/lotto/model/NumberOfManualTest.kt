package lotto.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NumberOfManualTest {
    @ParameterizedTest
    @CsvSource("0:2", "3:3", "10:11", delimiter = ':')
    fun `수동으로 발행할 로또의 개수는 0 이상의 정수여야 한다`(
        manualCounts: Int,
        totalCounts: Int,
    ) {
        assertDoesNotThrow { NumberOfManual(manualCounts, totalCounts) }
    }

    @ParameterizedTest
    @CsvSource("2:1", "5:3", "11:10", delimiter = ':')
    fun `수동으로 발행할 로또의 개수는 구매한 로또 개수를 넘길 수 없다`(
        manualCounts: Int,
        totalCounts: Int,
    ) {
        assertThrows<IllegalArgumentException> { NumberOfManual(manualCounts, totalCounts) }
    }
}
