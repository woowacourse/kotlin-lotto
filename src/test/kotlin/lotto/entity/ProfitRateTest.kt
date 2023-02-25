package lotto.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitRateTest {
    @Test
    fun `수익률이 0_3525일때 내림을 실행하면 0_35가 된다`() {
        // given
        val profitRate = ProfitRate(0.35f)

        // when
        val roundDownedProfitRate = profitRate.roundDown()
        val except = 0.35f

        // then
        assertThat(roundDownedProfitRate).isEqualTo(except)
    }
}
