package lottogame.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LottoCountTest {
    @Test
    fun `lottoPrice * count 가 cost 이하면, LottoCount 생성`() {
        // given
        val lottoPrice = Money(1_000)
        val count = Count(1)
        val cost = Money(1_000)
        // then
        assertDoesNotThrow { LottoCount.ofNullable(count, lottoPrice, cost) }
    }

    @Test
    fun `lottoPrice * count 가 cost 보다 크면, null 을 반환한다`() {
        // given
        val lottoPrice = Money(1_001)
        val count = Count(1)
        val cost = Money(1_000)
        // when
        val lotto = LottoCount.ofNullable(count, lottoPrice, cost)
        // then
        assertThat(lotto).isNull()
    }
}
