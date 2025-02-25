package lotto.domain.model

import lotto.domain.service.PurchaseCalculator
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PurchaseCalculatorTest {
    private lateinit var purchaseCalculator: PurchaseCalculator

    @BeforeEach
    fun setUp() {
        purchaseCalculator = PurchaseCalculator()
    }

    @CsvSource(
        "1000, 1",
        "10000, 10",
        "14000, 14",
    )
    @ParameterizedTest(name = "{0}원으로, {1}개를 구매할 수 있다")
    fun `구매 금액으로 구매할 로또 개수를 계산할 수 있다`(
        amount: Int,
        count: Int,
    ) {
        val lottoCount = purchaseCalculator.getLottoCount(amount)
        assertThat(lottoCount).isEqualTo(count)
    }

    @ValueSource(ints = [999, 0, -1000, -999])
    @ParameterizedTest
    fun `구매 금액이 1000원 미만이라면 예외가 발생한다`(purchaseAmount: Int) {
        assertThatThrownBy { purchaseCalculator.getLottoCount(purchaseAmount) }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${purchaseAmount}원으로 로또를 구매하지 못했습니다 로또는 한 장 이상 구매해야 합니다.")
    }
}
