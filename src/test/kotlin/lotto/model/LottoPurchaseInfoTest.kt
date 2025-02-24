package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoPurchaseInfoTest {
    @Test
    fun `구입 금액이 천원 단위가 아닌 경우 예외가 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> { LottoPurchaseInfo(1100, 0) }
        assertThat(exception.message).isEqualTo("[ERROR] 구입 금액은 천원 단위여야 합니다.")
    }

    @Test
    fun `구입 금액이 천원 미만인 경우 예외가 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> { LottoPurchaseInfo(900, 0) }
        assertThat(exception.message).isEqualTo("[ERROR] 구입 금액이 최소 금액보다 작습니다.")
    }

    @Test
    fun `구입 금액을 로또 가격으로 나누면 로또 수량과 같다`() {
        val amount = LottoPurchaseInfo(10000, 0)
        assertThat(amount.getTotalLottoQuantity()).isEqualTo(10000 / LottoPurchaseInfo.LOTTO_PRICE)
    }

    @Test
    fun `총 구매 수량에서 수동 로또 수량을 빼면 자동 로또 수량과 같다`() {
        val amount = LottoPurchaseInfo(10000, 3)
        assertThat(amount.getAutoLottoQuantity()).isEqualTo((10000 / LottoPurchaseInfo.LOTTO_PRICE) - 3)
    }
}
