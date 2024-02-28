package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PurchaseAmountTest {
    @Test
    fun `수동으로 발행할 로또 개수는 구매 가능한 로또의 총 개수보다 크다면, 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { PurchaseAmount(4000, 5) }
    }

    @Test
    fun `수동으로 발행할 로또 개수는 구매 가능한 로또의 총 개수보다 작거나 같다면, 정상작동 한다`() {
        assertDoesNotThrow { PurchaseAmount(4000, 4) }
    }

    @Test
    fun `총 구매한 로또 개수 중에서 수동 구매를 제외한 나머지 개수는 자동 발행 개수 이다`() {
        val purchaseAmount = PurchaseAmount(4000, 3)
        val result = purchaseAmount.getNumberOfAutoTickets()
        assertThat(result).isEqualTo(1)
    }
}
