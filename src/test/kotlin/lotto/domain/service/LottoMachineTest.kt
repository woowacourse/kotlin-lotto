package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.PurchaseAmount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    private val lottoMachine = LottoMachine()

    @Test
    fun `구매 금액 5000원에 대해 5장의 로또를 발급한다`() {
        val purchaseAmount = PurchaseAmount(5000)
        val lottos: List<Lotto> = lottoMachine.generate(purchaseAmount)
        assertThat(lottos.size).isEqualTo(5)
    }
}
