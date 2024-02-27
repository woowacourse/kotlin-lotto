package lottogame.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoCountTest {
    @Test
    fun `lottoPrice * count 가 cost 이하면, LottoCount 생성`() {
        // given
        val lottoPrice = Money(1_000)
        val count = 1
        val cost = Money(1_000)
        // then
        assertDoesNotThrow { LottoCount.of(count, lottoPrice, cost) }
    }

    @Test
    fun `lottoPrice * count 가 cost 보다 크면, 예외 발생`() {
        // given
        val lottoPrice = Money(1_001)
        val count = 1
        val cost = Money(1_000)
        // then
        assertThrows<IllegalStateException> { LottoCount.of(count, lottoPrice, cost) }
    }
}
