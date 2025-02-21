package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.PurchaseAmount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 구매 수량에 해당하는 만큼의 로또를 발급한다`() {
        val purchaseAmount = PurchaseAmount(5000)
        val lottoMachine = LottoMachine()
        val lottos: List<Lotto> = lottoMachine.generate(purchaseAmount)
        assertThat(lottos.size).isEqualTo(5)
    }
}
