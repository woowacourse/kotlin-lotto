package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoPurchaseInfoTest {
    @Test
    fun `총 구매 수량에서 수동 로또 수량을 빼면 자동 로또 수량과 같다`() {
        val lottoPurchaseInfo = LottoPurchaseInfo(Amount(10000), 3)
        assertThat(lottoPurchaseInfo.getAutoLottoQuantity()).isEqualTo((10000 / 1000) - 3)
    }

    @Test
    fun `수동 로또 수량은 총 구매 수량보다 작거나 같다`() {
        val exception = assertThrows<IllegalArgumentException> { LottoPurchaseInfo(Amount(1000), 2) }
        assertThat(exception.message).isEqualTo("[ERROR] 수동 구매 수량은 총 구매 수량보다 클 수 없습니다.")
    }
}
