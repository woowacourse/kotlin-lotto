package lotto.domain

import lotto.domain.model.LottoMachine
import lotto.domain.model.Lottos
import lotto.domain.service.RandomLottoNumbersGenerator
import lotto.domain.value.LottoPayInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoMachineTest {
    private lateinit var fake100ManualLottoNumbers: List<List<Int>>

    @BeforeEach
    fun setUp() {
        val randomLottoNumbersGenerator = RandomLottoNumbersGenerator()
        fake100ManualLottoNumbers = List(100) { randomLottoNumbersGenerator.generateLottoNumbers().map { it.number } }
    }

    @ParameterizedTest
    @CsvSource(
        "5000, 5, 0",
        "5000, 0, 5",
        "100000, 49, 51",
    )
    fun `로또 구매 수량에 해당하는 만큼의 자동 및 수동 로또를 발급한다`(
        amount: Int,
        autoQuantity: Int,
        manualQuantity: Int,
    ) {
        // Given
        val lottoPayInfo = LottoPayInfo(amount, manualQuantity)
        val lottoMachine = LottoMachine()

        // When
        val lottos: Lottos =
            lottoMachine.generateLottos(lottoPayInfo, fake100ManualLottoNumbers.subList(0, manualQuantity))
        val ticketsSize = lottos.tickets.size

        // Then
        assertThat(ticketsSize).isEqualTo(autoQuantity + manualQuantity)
    }
}
