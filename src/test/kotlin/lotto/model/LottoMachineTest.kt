package lotto.model

import lotto.exception.Exceptions.ManualPurchaseCountTooLargeException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoMachineTest {
    @ParameterizedTest
    @CsvSource("1000", "2000", "6000", "8000")
    fun `구입할 수 있는 로또 개수보다 수동으로 구매할 로또 개수가 크면 ManualPurchaseCountTooLargeException 예외처리가 발생한다`(strMoney: String) {
        val lottoMachine = LottoMachine(Price.from(strMoney))

        assertThrows<ManualPurchaseCountTooLargeException> {
            lottoMachine.getRandomLottoCount(10)
        }
    }
}
