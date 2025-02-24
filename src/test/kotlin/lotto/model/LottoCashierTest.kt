package lotto.model

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoCashierTest {
    @ValueSource(ints = [-1, 0])
    @ParameterizedTest
    fun `입력한 금액은 0원 이상만 가능하다`(amount: Int) {
        assertThatThrownBy {
            LottoCashier(0)
        }.hasMessageContaining("0원 이상의 금액")
    }

    @Test
    fun `입력한 금액이 1,000으로 나누어지지 않으면 실패한다`() {
        assertThatThrownBy {
            LottoCashier(1001)
        }.hasMessageContaining("단위")
    }

    @Test
    fun `구입 금액이 5,000원이면 로또 구입 개수를 5개로 반환한다`() {
        val lottoCashier = LottoCashier(amount = 5000)
        val lottoQuantity = lottoCashier.getPurchaseQuantity()

        assertEquals(5, lottoQuantity)
    }
}
