package lotto.model

import lotto.util.NumbersGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoMachineTest {
    @ParameterizedTest
    @CsvSource("1", "2", "3", "10")
    fun `발행 개수 만큼 로또를 발행한다`(count: Int) {
        val numbersGenerator =
            object : NumbersGenerator {
                override fun generateNumbers(count: Int): List<Set<Int>> {
                    return List(count) { setOf(1, 2, 3, 4, 5, 6) }
                }
            }
        val result = LottoMachine.issueAutoTickets(count, numbersGenerator)
        assertThat(result.size).isEqualTo(count)
        result.forEach {
            assertThat(it).isEqualTo(Lotto(1, 2, 3, 4, 5, 6))
        }
    }

    @Test
    fun `사용자가 입력한 번호를 토대로 로또를 발행한다`() {
        val input = listOf(setOf(1, 2, 3, 4, 5, 6), setOf(2, 3, 4, 5, 6, 7))
        val result = LottoMachine.issueTickets(input)
        val expected = listOf(Lotto(1, 2, 3, 4, 5, 6), Lotto(2, 3, 4, 5, 6, 7))
        assertThat(result).isEqualTo(expected)
    }
}
