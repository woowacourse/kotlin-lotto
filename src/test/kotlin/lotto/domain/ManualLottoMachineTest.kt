package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.lottomachine.ManualLottoMachine
import lotto.domain.value.LottoNumber
import lotto.domain.value.LottoPayInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ManualLottoMachineTest {
    @ParameterizedTest
    @CsvSource(
        "5000, 0, 5",
        "100000, 49, 51",
    )
    fun `구매 정보의 수동로또 구매 수량에 해당하는 만큼 로또 티켓들을 발급한다`(
        amount: Int,
        autoQuantity: Int,
        manualQuantity: Int,
    ) {
        // Given
        val lottoPayInfo = LottoPayInfo(amount, manualQuantity)
        val manualLottoList = List(manualQuantity) { (1..6).map { LottoNumber(it) } }
        val manualLottoMachine = ManualLottoMachine()

        // When
        val lottoTickets: List<Lotto> =
            manualLottoMachine.generateLottoBundle(lottoPayInfo, manualLottoList)
        val ticketsSize = lottoTickets.size

        // Then
        assertThat(ticketsSize).isEqualTo(manualQuantity)
    }
}
