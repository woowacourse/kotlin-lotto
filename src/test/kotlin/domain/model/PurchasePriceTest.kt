package domain.model

import domain.model.PurchasePrice.Companion.toPurchasePriceResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchasePriceTest {
    @ParameterizedTest
    @ValueSource(ints = [0, -1000])
    fun `구입 금액이 0 이하면 InvalidMinimumPurchaseAmount 씰드 클래스를 반환한다`(value: Int) {
        val actualResult = value.toPurchasePriceResult()

        val expectedResult = PurchasePriceResult.InvalidMinimumPurchaseAmount::class.java

        assertThat(actualResult).isInstanceOf(expectedResult)
    }

    @Test
    fun `구입 금액이 천원 단위가 아니면 InvalidThousandWonUnit 씰드 클래스를 반환한다`() {
        val actualResult = 1500.toPurchasePriceResult()

        val expectedResult = PurchasePriceResult.InvalidThousandWonUnit::class.java

        assertThat(actualResult).isInstanceOf(expectedResult)
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 35000])
    fun `이외 정상적인 구입 금액을 입력하면 Success 씰드 클래스를 반환한다`(value: Int) {
        val actualResult = value.toPurchasePriceResult()

        val expectedResult = PurchasePriceResult.Success::class.java

        assertThat(actualResult).isInstanceOf(expectedResult)
    }
}
