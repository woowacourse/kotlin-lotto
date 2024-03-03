package lottogame.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoCountTest {
    @Test
    fun `amount 가 0 미만이면, LottoCount 생성 불가능`() {
        assertAll(
            { assertThat(LottoCount.fromNullable(MINUS)).isNull() },
            { assertThrows<IllegalArgumentException> { LottoCount.from(MINUS) } },
        )
    }

    @Test
    fun `amount 가 0 이상 이면, LottoCount 생성`() {
        assertAll(
            { assertThat(LottoCount.fromNullable(0)).isNotNull },
            { assertDoesNotThrow { LottoCount.from(0) } },
        )
    }

    @Test
    fun `cost 를 lottoPrice 로 나눈 몫이 count 이상 이면, LottoCount 생성`() {
        // given
        val cost = Money(1_000)
        val lottoPrice = Money(1_000)
        val count = LottoCount.from(1)
        // when
        val lotto = LottoCount.ofNullable(count, lottoPrice, cost)
        // then
        assertThat(lotto).isEqualTo(count)
    }

    @Test
    fun `cost 를 lottoPrice 로 나눈 몫이 count 미만 이면, null 반환`() {
        // given
        val cost = Money(1_000)
        val lottoPrice = Money(1_001)
        val count = LottoCount.from(1)
        // when
        val lotto = LottoCount.ofNullable(count, lottoPrice, cost)
        // then
        assertThat(lotto).isNull()
    }

    companion object {
        private const val MINUS = -1
    }
}
