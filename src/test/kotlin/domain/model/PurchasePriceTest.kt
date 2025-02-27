package domain.model

import domain.model.PurchasePrice.Companion.toPurchasePriceResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchasePriceTest {
    @Test
    fun `구입 금액이 0 이하면 InvalidMinimumPurchaseAmount 씰드 클래스를 반환한다`() {
        val actualResult = 0.toPurchasePriceResult()

        val expectedResult = PurchasePriceResult.InvalidMinimumPurchaseAmount::class.java

        assertThat(actualResult).isInstanceOf(expectedResult)
    }

    @Test
    fun `구입 금액이 천원 단위가 아니면 InvalidThousandWonUnit 씰드 클래스를 반환한다`() {
        val actualResult = 1500.toPurchasePriceResult()

        val expectedResult = PurchasePriceResult.InvalidThousandWonUnit::class.java

        assertThat(actualResult).isInstanceOf(expectedResult)
    }
}
