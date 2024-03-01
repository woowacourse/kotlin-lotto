package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoCountTest {
    @Test
    fun `구입 금액과 수동으로 구매할 로또 개수에 따라 자동으로 구매할 로또 개수를 구한다`() {
        val lottoCount = LottoCount(14000, LOTTO_PRICE, MANUAL_COUNT)
        assertThat(lottoCount.autoCount).isEqualTo(13)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1000, 0, 1100])
    fun `잘못된 구입 금액을 입력할 경우 예외가 발생한다`(purchasePrice: Int) {
        assertThrows<IllegalArgumentException> {
            LottoCount(purchasePrice, LOTTO_PRICE, MANUAL_COUNT)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1000, 0])
    fun `잘못된 로또 가격을 입력할 경우 예외가 발생한다`(lottoPrice: Int) {
        assertThrows<IllegalArgumentException> {
            LottoCount(PURCHASE_PRICE, lottoPrice, MANUAL_COUNT)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-2, 11])
    fun `잘못된 수동 로또 구매 수를 입력할 경우 예외가 발생한다`(manualCount: Int) {
        assertThrows<IllegalArgumentException> {
            LottoCount(PURCHASE_PRICE, LOTTO_PRICE, manualCount)
        }
    }

    companion object {
        private const val PURCHASE_PRICE = 1000
        private const val LOTTO_PRICE = 1000
        private const val MANUAL_COUNT = 1
    }
}
