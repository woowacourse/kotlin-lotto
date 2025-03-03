package lotto.domain

import io.kotest.matchers.shouldBe
import lotto.domain.model.Amount
import lotto.domain.model.LottoPurchaseInfo
import org.junit.jupiter.api.Test

class AutoLottoMachineTest {
    @Test
    fun `수량에 맞게 자동으로 로또를 발행한다`() {
        // Given
        val lottoPurchaseAmount = LottoPurchaseInfo(Amount(3000), 0)
        val autoLottoMachine = AutoLottoMachine()

        // When
        val autoLottoTickets = autoLottoMachine.publishLottoTickets(lottoPurchaseAmount)

        // Then
        autoLottoTickets.size shouldBe 3
    }
}
