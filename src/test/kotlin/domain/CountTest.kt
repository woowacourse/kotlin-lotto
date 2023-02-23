package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CountTest {
    @ParameterizedTest
    @CsvSource("-1", "-10", "-20")
    fun `개수가 음수가 되서는 안된다`(value: Int) {
        val exception = assertThrows<IllegalArgumentException> { Count(value) }
        assertEquals(Count.COUNT_NEGATIVE_ERROR_MESSAGE, exception.message)
    }
}
