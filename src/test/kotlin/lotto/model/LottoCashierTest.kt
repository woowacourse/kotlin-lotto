package lotto.model

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoCashierTest {
    @ValueSource(ints = [-1, 0])
    @ParameterizedTest
    fun `입력한 금액은 0원 이상만 가능하다`(amount: Int) {
        // given & when & then
        assertThatThrownBy {
            LottoCashier(amount = amount, manualQuantity = 0)
        }.hasMessageContaining("0원 이상의 금액")
    }

    @Test
    fun `입력한 금액이 1,000으로 나누어지지 않으면 실패한다`() {
        // given
        val amount = 1001

        // when & then
        assertThatThrownBy {
            LottoCashier(amount = amount, manualQuantity = 0)
        }.hasMessageContaining("단위")
    }

    @Test
    fun `구입 금액이 5,000원이면 로또 구입 개수를 5개로 반환한다`() {
        // given
        val amount = 5000
        val lottoCashier = LottoCashier(amount = amount, manualQuantity = 0)

        // when
        val lottoQuantity = lottoCashier.getPurchaseAutoQuantity()

        // then
        assertEquals(5, lottoQuantity)
    }

    @Test
    fun `구입 금액보다 많은 수동 로또를 요구하면 오류를 반환한다`() {
        // given
        val amount = 1000
        val manualQuantity = 2

        // when & then
        assertThrows<IllegalArgumentException> {
            LottoCashier(amount, manualQuantity)
        }
    }
}
