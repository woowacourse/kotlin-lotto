package lottogame.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoCountTest {
    @Test
    fun `amount 가 0 미만이면, LottoCount 생성 불가능`() {
        assertThrows<IllegalArgumentException> { LottoCount(MINUS) }
    }

    @Test
    fun `amount 가 0 이상 이면, LottoCount 생성`() {
        assertDoesNotThrow { LottoCount(0) }
    }

    @Test
    fun `cost 를 lottoPrice 로 나눈 몫이 count 이상 이면, LottoCount 생성`() {
        // given
        val cost = Money(1_000)
        val lottoPrice = Money(1_000)
        val count = LottoCount(1)
        // when
        val lotto = LottoCount(count, lottoPrice, cost)
        // then
        assertThat(lotto).isEqualTo(count)
    }

    @Test
    fun `cost 를 lottoPrice 로 나눈 몫이 count 미만 이면, 에러 발생`() {
        // given
        val cost = Money(1_000)
        val lottoPrice = Money(1_001)
        val count = LottoCount(1)
        // then
        assertThrows<IllegalStateException> { LottoCount(count, lottoPrice, cost) }
    }

    companion object {
        private const val MINUS = -1
    }
}
