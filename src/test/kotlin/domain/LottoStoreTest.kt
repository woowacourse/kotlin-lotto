package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStoreTest {

    @Test
    fun `로또 상점은 로또 금액을 받으면 금액에서 로또 가격을 나눈 값만큼의 개수의 로또들을 반환한다`() {
        val money = Money(10000)

        val result = LottoStore.sell(money).size

        assertThat(result).isEqualTo(money / Money(LottoStore.LOTTO_PRICE))
    }
}
