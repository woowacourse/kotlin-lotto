package lottotest.domain.model.winning

import lotto.domain.model.winning.GainLoss
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GainLossTest {
    @ParameterizedTest
    @CsvSource(
        "0.99, LOSS",
        "1.00, PRINCIPAL",
        "1.01, GAIN",
    )
    fun `수익률이 1을 기준으로 작으면 LOSS 크면 GAIN, 같으면 PRINCIPAL이다`(
        rate: Double,
        expected: GainLoss,
    ) {
        // When
        val actual = GainLoss.valueOf(rate)

        // Then
        assertThat(actual).isEqualTo(expected)
    }
}
