package lotto.domain.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 3, 4])
    fun `로또 구입 개수만큼 로또를 발행한다`(count: Int) {
        val lottoTickets = LottoMachine().purchase(count)
        assertEquals(lottoTickets.size, count)
    }

    @Test
    fun `생성된 로또는 1에서 45범위 사이이다`() {
        val lottoTickets = LottoMachine().purchase(1)
        assertDoesNotThrow {
            lottoTickets[0].getNumbers().all { it.number in 1..45 }
        }
    }
}
