package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTicketCountTest {
    @ParameterizedTest
    @ValueSource(ints = [-1])
    fun `로또 구매 장수에 음수가 들어오면 에러가 발생한다`(count: Int) {
        assertThrows<IllegalArgumentException> {
            LottoTicketCount(count)
        }
    }
}
