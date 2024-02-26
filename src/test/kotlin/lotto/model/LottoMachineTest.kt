package lotto.model

import lotto.util.NumbersGenerator
import org.assertj.core.api.Assertions.assertThat
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
        val numbersGenerator =
            object : NumbersGenerator {
                override fun generateNumbers(): List<Int> {
                    return listOf(1, 2, 3, 4, 5, 6)
                }
            }
        val result = LottoMachine.issueTickets(count, numbersGenerator)
        assertThat(result.size).isEqualTo(count)
        result.forEach {
            assertThat(it).isEqualTo(Lotto.of(1, 2, 3, 4, 5, 6))
        }
    }
}
