package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LottoDrawingResultTest {

    @Nested
    @DisplayName("수익률을 계산하는 calculateMarginRate 메서드 테스트")
    inner class CalculateMarginRateTest {

        @Test
        fun `구입 금액이 5천원이고 수익 금액이 5만원일 때 수익률 10을 반환한다`() {
            val drawingResult = LottoDrawingResult(
                mapOf(
                    Rank.FIRST to 0,
                    Rank.SECOND to 0,
                    Rank.THIRD to 0,
                    Rank.FOURTH to 1,
                    Rank.FIFTH to 0
                )
            )
            val purchaseAmount = Money(5000)
            val actual = drawingResult.calculateMarginRate(purchaseAmount)
            assertThat(actual).isEqualTo(Margin(10.0))
        }

        @Test
        fun `구입 금액이 14000원이고 수익 금액이 5천원일 때 수익률 0,35714285714285715를 반환한다`() {
            val drawingResult = LottoDrawingResult(
                mapOf(
                    Rank.FIRST to 0,
                    Rank.SECOND to 0,
                    Rank.THIRD to 0,
                    Rank.FOURTH to 0,
                    Rank.FIFTH to 1
                )
            )
            val purchaseAmount = Money(14000)
            val actual = drawingResult.calculateMarginRate(purchaseAmount)
            assertThat(actual).isEqualTo(Margin(0.35714285714285715))
        }
    }

    @Nested
    @DisplayName("총 상금을 계산하는 calculateTotalPrize 메서드 테스트")
    inner class CalculateTotalPrizeTest {

        @Test
        fun `1등이 1개일 때 총 상금으로 20억을 반환하는지 테스트`() {
            val drawingResult = LottoDrawingResult(mapOf(Rank.FIRST to 1))
            val actual = drawingResult.calculateTotalPrize()
            assertThat(actual).isEqualTo(Money(2_000_000_000))
        }

        @Test
        fun `2등이 1개일 때 총 상금으로 3천만을 반환하는지 테스트`() {
            val drawingResult = LottoDrawingResult(mapOf(Rank.SECOND to 1))
            val actual = drawingResult.calculateTotalPrize()
            assertThat(actual).isEqualTo(Money(30_000_000))
        }

        @Test
        fun `3등이 1개일 때 총 상금으로 150만을 반환하는지 테스트`() {
            val drawingResult = LottoDrawingResult(mapOf(Rank.THIRD to 1))
            val actual = drawingResult.calculateTotalPrize()
            assertThat(actual).isEqualTo(Money(1_500_000))
        }

        @Test
        fun `4등이 1개일 때 총 상금으로 5만을 반환하는지 테스트`() {
            val drawingResult = LottoDrawingResult(mapOf(Rank.FOURTH to 1))
            val actual = drawingResult.calculateTotalPrize()
            assertThat(actual).isEqualTo(Money(50_000))
        }

        @Test
        fun `5등이 1개일 때 총 상금으로 5천을 반환하는지 테스트`() {
            val drawingResult = LottoDrawingResult(mapOf(Rank.FIFTH to 1))
            val actual = drawingResult.calculateTotalPrize()
            assertThat(actual).isEqualTo(Money(5_000))
        }

        @Test
        fun `MISS가 1개일 때 총 상금으로 0을 반환하는지 테스트`() {
            val drawingResult = LottoDrawingResult(mapOf(Rank.MISS to 1))
            val actual = drawingResult.calculateTotalPrize()
            assertThat(actual).isEqualTo(Money(0))
        }

        @Test
        fun `각 랭크가 1개씩 존재할 때 총 상금으로 2_031_555_000을 반환하는지 테스트`() {
            val drawingResult = LottoDrawingResult(
                mapOf(
                    Rank.FIRST to 1,
                    Rank.SECOND to 1,
                    Rank.THIRD to 1,
                    Rank.FOURTH to 1,
                    Rank.FIFTH to 1,
                    Rank.MISS to 1
                )
            )
            val actual = drawingResult.calculateTotalPrize()
            assertThat(actual).isEqualTo(Money(2_031_555_000))
        }

        @Test
        fun `Int형의 범위를 넘어가는 총 상금이어도 정상 작동한다`() {
            val drawingResult = LottoDrawingResult(
                mapOf(
                    Rank.FIRST to 2,
                    Rank.SECOND to 0,
                    Rank.THIRD to 0,
                    Rank.FOURTH to 0,
                    Rank.FIFTH to 0
                )
            )
            val actual = drawingResult.calculateTotalPrize()
            assertThat(actual).isEqualTo(Money(4_000_000_000))
        }
    }
}
