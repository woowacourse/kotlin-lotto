package lottotest.domain.model.purchaseInfo.quantity

import lotto.domain.model.purchaseInfo.quantity.LottoQuantity
import lotto.domain.model.purchaseInfo.quantity.ManualLottoQuantity
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ManualLottoQuantityTest {
    @ParameterizedTest
    @CsvSource(
        "10, 11",
        "1, 2",
    )
    fun `수동 로또 수량은 구매한 로또 수량을 넘어설 수 없다`(
        boughtLottoQuantity: Int,
        manualLottoQuantity: Int,
    ) {
        val boughtLottoQuantityObject = LottoQuantity(boughtLottoQuantity)
        assertThrows<IllegalArgumentException> {
            ManualLottoQuantity(boughtLottoQuantityObject, manualLottoQuantity)
        }
    }
}
