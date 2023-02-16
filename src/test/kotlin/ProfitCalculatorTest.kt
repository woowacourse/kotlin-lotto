import domain.ProfitCalculator
import domain.model.LottoResult
import domain.model.PurchaseMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitCalculatorTest {

    @Test
    fun `수익률을 계산한다`() {
        val profitCalculator = ProfitCalculator()

        assertThat(
            profitCalculator.getProfit(
                PurchaseMoney(14000),
                listOf(LottoResult.FIFTH)
            )
        ).isEqualTo(0.35)
    }
}
