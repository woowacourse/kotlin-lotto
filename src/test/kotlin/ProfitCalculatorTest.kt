import domain.ProfitCalculator
import domain.model.LottoResult
import domain.model.PurchaseMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitCalculatorTest {

    @Test
    fun `수익률을 계산한다`() {
        // given
        val profitCalculator = ProfitCalculator()
        // when
        val actual = profitCalculator.getProfit(
            PurchaseMoney(14000),
            listOf(LottoResult.FIFTH)
        )
        // then
        assertThat(actual).isEqualTo(0.35)
    }
}
