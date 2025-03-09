package lottotest.domain.valueobject

import lotto.domain.valueobject.LottoQuantity
import lotto.domain.valueobject.ManualLottoQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ManualLottoQuantityTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 123546])
    fun `수동 로또 수량은 0 이상의 정수 값이다`(quantity: Int) {
        // given when
        val partialLottoQuantity = ManualLottoQuantity(quantity)

        // then
        assertThat(partialLottoQuantity.quantity).isEqualTo(quantity)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -1000])
    fun `수동 로또 수량은 음수가 될 수 없다`(quantity: Int) {
        assertThrows<IllegalArgumentException> {
            ManualLottoQuantity(quantity)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "10, 11",
        "1, 2",
    )
    fun `수동 로또 수량은 전체 로또 수량을 넘어설 수 없다`(
        lottoQuantity: Int,
        manualLottoQuantity: Int,
    ) {
        val lottoQuantityObject = LottoQuantity(lottoQuantity)
        assertThrows<IllegalArgumentException> {
            ManualLottoQuantity(lottoQuantityObject, manualLottoQuantity)
        }
    }
}
