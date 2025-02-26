package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoResultTest {
    @ParameterizedTest(name = "로또 등수가 [{0}]일 때, 수익률은 \"{1}\"이다")
    @CsvSource(
        "FIRST, 2000000.00",
        "SECOND, 30000.00",
        "THIRD, 1500.00",
        "FOURTH FIFTH, 27.50",
        "MISS, 0.00",
        "THIRD FIFTH SECOND, 10501.66",
    )
    fun `총 수익률을 계산할 수 있다`(
        lottoRanks: String,
        expectedReturn: Double,
    ) {
        val ranks = lottoRanks.split(" ").map { LottoRank.valueOf(it) }
        val totalReturn = LottoResult(ranks).calculateTotalReturn(1000)
        assertThat(totalReturn.toDouble()).isEqualTo(expectedReturn)
    }
}
