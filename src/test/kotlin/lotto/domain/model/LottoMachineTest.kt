package lotto.domain.model

import lotto.domain.value.PurchaseAmount
import lotto.domain.value.PurchaseQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 구매 수량에 해당하는 만큼의 로또를 발급한다`() {
        val purchaseAmount = PurchaseAmount(5000)
        val purchaseQuantity = PurchaseQuantity(purchaseAmount)
        val lottoMachine = LottoMachine()
        val lottos: List<Lotto> = lottoMachine.generateLottos(purchaseQuantity)
        assertThat(lottos.size).isEqualTo(5)
    }
}
