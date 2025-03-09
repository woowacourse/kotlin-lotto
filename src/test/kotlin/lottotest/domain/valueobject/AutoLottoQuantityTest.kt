package lottotest.domain.valueobject

import lotto.domain.valueobject.AutoLottoQuantity
import lotto.domain.valueobject.LottoQuantity
import lotto.domain.valueobject.ManualLottoQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class AutoLottoQuantityTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 123546])
    fun `자동 로또 수량은 0 이상의 정수 값이다`(quantity: Int) {
        // given when
        val partialLottoQuantity = AutoLottoQuantity(quantity)

        // then
        assertThat(partialLottoQuantity.quantity).isEqualTo(quantity)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -1000])
    fun `자동 로또 수량은 음수가 될 수 없다`(quantity: Int) {
        assertThrows<IllegalArgumentException> {
            AutoLottoQuantity(quantity)
        }
    }

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
        val manualLottoQuantityObject = ManualLottoQuantity(manualLottoQuantity)
        val expectedAutoLottoQuantityObject = AutoLottoQuantity(expectedAutoLottoQuantity)

        val actualAutoLottoQuantity = AutoLottoQuantity(lottoQuantityObject, manualLottoQuantityObject)

        assertThat(actualAutoLottoQuantity).isEqualTo(expectedAutoLottoQuantityObject)
    }
}
