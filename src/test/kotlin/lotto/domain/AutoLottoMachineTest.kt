package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.lottomachine.AutoLottoMachine
import lotto.domain.value.LottoPayInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AutoLottoMachineTest {
    @ParameterizedTest
    @CsvSource(
        "5000, 5, 0",
        "100000, 49, 51",
    )
    fun `구매 정보의 자동로또 구매 수량에 해당하는 만큼 로또 티켓들을 발급한다`(
        amount: Int,
        autoQuantity: Int,
        manualQuantity: Int,
    ) {
        // Given
        val lottoPayInfo = LottoPayInfo(amount, manualQuantity)
        val autoLottoMachine = AutoLottoMachine()

        // When
        val lottoTickets: List<Lotto> = autoLottoMachine.generateLottoBundle(lottoPayInfo)
        val ticketsSize = lottoTickets.size

        // Then
        assertThat(ticketsSize).isEqualTo(autoQuantity)
    }
}
