package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NumberOfTicketsTest {
    @ParameterizedTest
    @CsvSource("1000, 1", "2700, 2", "3150, 3", "10500, 10")
    fun `로또 발행 개수를 계산한다`(
        input: Int,
        expected: Int,
    ) {
        val actual = NumberOfTickets(input).counts
        assertThat(actual).isEqualTo(expected)
    }
}
