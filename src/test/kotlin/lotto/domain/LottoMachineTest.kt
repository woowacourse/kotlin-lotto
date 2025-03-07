package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 머신은 로또를 발행한다`() {
        val input = listOf(setOf(1, 2, 3, 4, 5, 6))
        val lottoMachine = LottoMachine()
        val actual = lottoMachine.createManualLottoTicket(input)
        val expected = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }.toSet()))
        assertThat(actual).isEqualTo(expected)
    }
}
