package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AutoLottoMachineTest {
    @ParameterizedTest
    @CsvSource(
        "5000, 5",
        "1000, 1",
        "15000, 15",
    )
    fun `구입 금액 만큼의 개수만큼 로또를 산다`(
        purchasedAmount: Int,
        purchasedCount: Int,
    ) {
        val autoLottoMachine = AutoLottoMachine(purchasedAmount)
        assertThat(autoLottoMachine.makeUserTickets().size).isEqualTo(purchasedCount)
    }
}
