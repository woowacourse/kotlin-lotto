package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchasePriceTest {
    @Test
    fun `10000원을 입력하면 구입 가능한 로또 개수 10개를 반환한다`() {
        val price = PurchasePrice(10000).getPurchasableLottoCount()
        assertThat(price).isEqualTo(10)
    }

    @Test
    fun `구입 금액이 0 이하면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            PurchasePrice(0)
        }.apply { assertThat(this).hasMessage("[ERROR] 1000원 이상 입력해주세요.") }
    }

    @Test
    fun `구입 금액이 천원 단위가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            PurchasePrice(10101010)
        }.apply { assertThat(this).hasMessage("[ERROR] 1000원 단위로 입력해주세요.") }
    }
}
