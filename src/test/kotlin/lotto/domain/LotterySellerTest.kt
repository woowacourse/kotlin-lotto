package lotto.domain

import lotto.LotterySeller
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotterySellerTest {

    @Test
    fun `구매 금액을 받아 금액에 맞는 개수의 로또를 발행한다`() {
        val seller: LotterySeller = LotterySeller()
        val amount: PurchaseAmount = PurchaseAmount(5000)

        assertThat(seller.generateLotteries(amount).size).isEqualTo(5)
    }
}
