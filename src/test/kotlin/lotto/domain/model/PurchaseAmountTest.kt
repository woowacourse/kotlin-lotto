package lotto.domain.model

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {
    private lateinit var purchaseAmount: PurchaseAmount

    @BeforeEach
    fun setUp() {
        purchaseAmount = PurchaseAmount.from(3000)
    }

    @CsvSource(
        "1000, 2",
        "10000, 12",
        "14000, 17",
    )
    @ParameterizedTest(name = "{0}원으로는, {1}개를 구매할 수 없다")
    fun `구매 금액보다 많은 개수를 구매할 경우에 예외가 발생한다`(
        amount: Int,
        count: Int,
    ) {
        this.purchaseAmount = PurchaseAmount.from(amount)
        val lottoAmount = count * Lotto.LOTTO_PRICE
        assertThatThrownBy { purchaseAmount.purchaseLotto(count) }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("선택하신 개수의 금액은 $lottoAmount 입니다. 구매하시는 금액은 현재 구매할 ${amount}원 보다 작거나 같아야 합니다.")
    }

    @ValueSource(ints = [999, 0, -1000, -999])
    @ParameterizedTest
    fun `구매 금액이 1000원 미만이라면 예외가 발생한다`(purchaseAmount: Int) {
        assertThatThrownBy { PurchaseAmount.from(purchaseAmount) }.isInstanceOf(
            IllegalArgumentException::class.java,
        ).hasMessage("${purchaseAmount}원으로 로또를 구매하지 못했습니다 로또는 한 장 이상 구매해야 합니다.")
    }
}
