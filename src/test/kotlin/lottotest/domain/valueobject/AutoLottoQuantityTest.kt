package lottotest.domain.valueobject

import lotto.domain.valueobject.AutoLottoQuantity
import lotto.domain.valueobject.LottoQuantity
import lotto.domain.valueobject.ManualLottoQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AutoLottoQuantityTest {
    @ParameterizedTest
    @CsvSource(
        "10, 5, 5",
        "1, 1, 0",
        "2, 0, 2",
    )
    fun `자동 로또 수량은 전체 로또 수량과 수동 로또 수량으로 계산된다`(
        lottoQuantity: Int,
        manualLottoQuantity: Int,
        expectedAutoLottoQuantity: Int,
    ) {
        val lottoQuantityObject = LottoQuantity(lottoQuantity)
        val manualLottoQuantityObject = ManualLottoQuantity(lottoQuantityObject, manualLottoQuantity)

        val actualAutoLottoQuantity = AutoLottoQuantity(lottoQuantityObject, manualLottoQuantityObject)

        assertThat(actualAutoLottoQuantity.quantity).isEqualTo(expectedAutoLottoQuantity)
    }
}
