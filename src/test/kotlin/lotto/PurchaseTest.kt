package lotto

import lotto.domain.LottoAmount
import lotto.domain.Purchase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseTest {
    @Test
    fun `구입 금액은 1000원 이상이어야 한다`() {
        val lottoPrice = 500
        assertThrows<IllegalArgumentException> { Purchase(lottoPrice) }
    }

    @Test
    fun `구입 금액에 맞는 로또 개수를 계산할 수 있다`() {
        val lottoPrice = 10000
        assertThat(Purchase(lottoPrice).calculateAmountOfLottos()).isEqualTo(LottoAmount(10))
    }

    @Test
    fun `1000원으로 나누어 떨어지지 않는 구입 금액에 따른 로또 개수를 계산할 수 있다`() {
        val lottoPrice = 9500
        assertThat(Purchase(lottoPrice).calculateAmountOfLottos()).isEqualTo(LottoAmount(9))
    }

    @Test
    fun `구입한 로또의 개수에 맞는 실제 구매 금액을 계산할 수 있다`() {
        val lottoPrice = 19500
        assertThat(Purchase(lottoPrice).getPrice()).isEqualTo(19000)
    }

    @Test
    fun `구입한 금액에 따른 로또의 개수는 1개 이상이어야 한다`() {
        val lottoPrice = 900
        assertThrows<IllegalArgumentException> { Purchase(lottoPrice).calculateAmountOfLottos() }
    }
}
