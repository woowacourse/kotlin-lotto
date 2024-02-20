package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoMachineTest {
    @ParameterizedTest
    @CsvSource("1000, 1", "2000, 2", "3000, 3", "10000, 10")
    fun `로또 발행 개수를 계산한다`(input: Int, expected: Int) {
        val actual = LottoMachine.getNumberOfTicket(input)
        assertThat(actual).isEqualTo(expected)
    }
}
