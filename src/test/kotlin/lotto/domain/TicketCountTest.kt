package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketCountTest {
    @Test
    fun `로또 개수를 가진다`() {
        assertThat(TicketCount(10).count).isEqualTo(10)
    }
}
