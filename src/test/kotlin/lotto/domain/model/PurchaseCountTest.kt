package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchaseCountTest {
    @Test
    fun `전체 로또 구입 개수에서 수동 구입 개수를 뺀 값이 자동 구입 개수이다`() {
        val autoCount = PurchaseCount.from(12, 9).autoCount
        assertThat(autoCount).isEqualTo(3)
    }
}
