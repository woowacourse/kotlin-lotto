package domain.model

import domain.model.PurchasePrice.Companion.toPurchasePriceResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchasePriceTest {
    @ParameterizedTest
    @ValueSource(ints = [0, -100, -3000])
    fun `구입 금액이 최소 금액 미만일 경우 예외 처리를 한다`(value: Int) {
        val actualResult = value.toPurchasePriceResult()

        val expectedResult = PurchasePriceResult.InvalidMinimumPurchaseAmount::class.java

        assertThat(actualResult).isInstanceOf(expectedResult)
    }

    @ParameterizedTest
    @ValueSource(ints = [500, 2500])
    fun `구입 금액은 천원 단위여야 한다`(value: Int) {
        val actualResult = value.toPurchasePriceResult()

        val expectedResult = PurchasePriceResult.InvalidThousandWonUnit::class.java

        assertThat(actualResult).isInstanceOf(expectedResult)
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 35000])
    fun `천원 단위의 양수로 정상적으로 로또를 구입할 수 있다`(value: Int) {
        val actualResult = value.toPurchasePriceResult()

        val expectedResult = PurchasePriceResult.Success::class.java

        assertThat(actualResult).isInstanceOf(expectedResult)
    }
}
