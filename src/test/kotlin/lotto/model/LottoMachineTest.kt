package lotto.model

import lotto.util.NumbersGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class LottoMachineTest {
    @Test
    fun `수동, 자동 개수에 맞게 로또를 발행하여 한번에 반환한다`() {
        val manualNumber = listOf(setOf(1, 2, 3, 4, 5, 6), setOf(1, 2, 3, 4, 5, 6))
        val lottoCount = LottoCount(numberOfTotalLotto = 6, numberOfManualLotto = 2)
        val autoNumbersGenerator =
            object : NumbersGenerator {
                override fun generateNumbers(): List<Int> {
                    return listOf(7, 8, 9, 10, 11, 12)
                }
            }
        val result = LottoMachine.issueLotto(lottoCount, manualNumber, autoNumbersGenerator)

        assertAll(
            { assertThat(result.size).isEqualTo(6) },
            { assertThat(result.filter { it == Lotto(1, 2, 3, 4, 5, 6) }.size).isEqualTo(2) },
            { assertThat(result.filter { it == Lotto(7, 8, 9, 10, 11, 12) }.size).isEqualTo(4) },
        )
    }
}
