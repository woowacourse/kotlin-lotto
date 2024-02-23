package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PrizeCountTest {
    @Test
    fun `당첨 개수는 0 이상이어야 한다`() {
        assertThrows<IllegalStateException> {
            PrizeCount(-1)
        }
    }

    @Test
    fun `당첨 개수를 증가한다`() {
        var prizeCount = PrizeCount()
        prizeCount += PrizeCount(1)
        assertThat(prizeCount).isEqualTo(PrizeCount(1))
    }
}
