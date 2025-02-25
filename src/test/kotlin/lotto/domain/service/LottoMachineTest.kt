package lotto.domain.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 3, 4])
    fun `로또 구입 개수만큼 로또를 발행한다`(count: Int) {
        val lottoTickets = LottoMachine().purchase(count)
        assertEquals(lottoTickets.size, count)
    }
}
