package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AutoLottoMachineTest {
    @ParameterizedTest
    @CsvSource(
        "5000, 5, 2",
        "7000, 7, 5",
        "15000, 15, 10",
    )
    fun `구입 금액에서 수동로또 개수를 뺀 만큼 자동로또를 산다`(
        purchasedPrice: Int,
        autoLottoCount: Int,
        manualLottoCount: Int,
    ) {
        val autoLottoMachine = AutoLottoMachine(purchasedPrice, manualLottoCount)
        assertThat(autoLottoMachine.makeAutoTickets().size).isEqualTo(autoLottoCount - manualLottoCount)
    }
}
