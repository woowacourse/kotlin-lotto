package lotto.domain

import lotto.domain.model.LottoMachine
import lotto.domain.model.Lottos
import lotto.domain.value.LottoPayInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 구매 수량에 해당하는 만큼의 로또를 발급한다`() {
        // Given
        val lottoPayInfo = LottoPayInfo(5000)
        val lottoMachine = LottoMachine()
        val lottos: Lottos = lottoMachine.generateLottos(lottoPayInfo)

        // When
        val ticketsSize = lottos.tickets.size

        // Then
        assertThat(ticketsSize).isEqualTo(5)
    }
}
