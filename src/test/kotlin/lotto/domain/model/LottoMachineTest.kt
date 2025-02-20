package lotto.domain.model

import lotto.domain.value.PurchaseAmount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 구매 수량에 해당하는 만큼의 로또를 발급한다`() {
        val purchaseAmount = PurchaseAmount(5000)
        val lottoMachine = LottoMachine()
        val purchaseDetail = lottoMachine.generateLottos(purchaseAmount)
        val lottos: List<Lotto> = purchaseDetail.lottos
        assertThat(lottos.size).isEqualTo(5)
    }
}
