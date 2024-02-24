package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoPurchaseTest {
    @ParameterizedTest
    @ValueSource(ints = [1000, 5000, 14000, 35000])
    fun `구입 금액 만큼 로또를 산다 (개수)`(purchased: Int) {
        val actual = LottoPurchase(purchased)
        assertThat(actual.makeUserTickets().size).isEqualTo(purchased / LottoPurchase.PRICE_OF_LOTTO_TICKET)
    }
}
