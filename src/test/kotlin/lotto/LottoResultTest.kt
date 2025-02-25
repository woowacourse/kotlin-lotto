package lotto

import lotto.model.LottoResult
import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class LottoResultTest {
    @Test
    fun `총 당첨금과 구매 금액을 기반으로 수익률을 계산한다`() {
        // given
        val ranks: Map<Rank, Int> =
            mapOf(
                Rank.THIRD to 1,
                Rank.FOURTH to 2,
            )
        val lottoResult = LottoResult(ranks)
        val purchaseAmount = 100_000

        // when
        val rateOfReturn = lottoResult.getRateOfReturn(purchaseAmount)

        // then
        val expectedRate = 16.0
        assertThat(rateOfReturn).isEqualTo(expectedRate)
    }

    @Test
    fun `getIsLossMoney는 수익률이 1보다 작으면 true를 1 이상이면 false를 반환한다`() {
        // given
        val profitRanks: Map<Rank, Int> = mapOf(Rank.SECOND to 1) // 당첨금 30_000_000
        val lossRanks: Map<Rank, Int> = mapOf(Rank.FIFTH to 1) // 당첨금 5_000
        val purchaseAmount = 10_000

        val profitResult = LottoResult(profitRanks)
        val lossResult = LottoResult(lossRanks)

        // when & then
        assertAll(
            { assertThat(profitResult.getIsLossMoney(profitResult.getRateOfReturn(purchaseAmount))).isFalse() },
            { assertThat(lossResult.getIsLossMoney(lossResult.getRateOfReturn(purchaseAmount))).isTrue() },
        )
    }
}
