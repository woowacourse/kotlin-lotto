package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RevenueTest {
    @ParameterizedTest
    @CsvSource("10.0,GAIN", "1,NOTHING", "0.35,LOSS")
    fun `손해인지, 이득인지 알려준다`(profit: Double, revenue: Revenue) {
        assertThat(Revenue.valueOf(profit)).isEqualTo(revenue)
    }
}
