package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchasePriceTest {
    @Test
    fun `10000원을 입력하면 구입 가능한 로또 개수 10개를 반환한다`(){
        val price = PurchasePrice(10000).getPurchasableLottoCount()
        assertThat(price).isEqualTo(10)
    }
}