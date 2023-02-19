package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RevenueTest {
    @ParameterizedTest
    @CsvSource(
        "10.00,GAIN, 수익률이 10.00이면 이득이다",
        "1.00,NOTHING, 수익률이 1.00이면 손해도 이득도 아니다",
        "0.35,LOSS, 수익률이 0.35면 손해이다"
    )
    fun `손해인지, 이득인지 알려준다`(profit: Double, revenue: Revenue, testName: String) {
        println(testName)

        assertThat(Revenue.valueOf(profit)).isEqualTo(revenue)
    }
}
