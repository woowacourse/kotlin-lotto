package lotto.domain

import io.kotest.matchers.shouldBe
import lotto.domain.model.LottoPurchaseInfo
import org.junit.jupiter.api.Test

class ManualLottoMachineTest {
    @Test
    fun `사용자가 로또 번호를 입력하면 로또를 발행한다`() {
        // Given
        val lottoPurchaseInfo = LottoPurchaseInfo(2000, 2)
        val manualLottoMachine = ManualLottoMachine(listOf(listOf(1, 2, 3, 4, 5, 6), listOf(7, 8, 9, 10, 11, 12)))

        // When
        val manualLottoTickets = manualLottoMachine.publishLottoTickets(lottoPurchaseInfo)

        // Then
        manualLottoTickets.size shouldBe 2
    }
}
