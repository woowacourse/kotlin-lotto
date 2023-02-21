package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStoreTest {

    @Test
    fun `5000원을 지불하고 수동을 넣지 않으면 총 5줄의 로또를 살 수 있다`() {
        assertThat(
            LottoStore(LottoFactory()).buyLottoes(
                PurchaseMoney(5000),
            ).value,
        )
            .hasSize(5)
    }

    @Test
    fun `5000원을 지불하고 수동 1개를 넣으면 총 5줄의 로또를 살 수 있다`() {
        assertThat(
            LottoStore(LottoFactory()).buyLottoes(
                PurchaseMoney(5000),
                listOf(listOf(1, 2, 3, 4, 5, 6)),
            ).value,
        )
            .hasSize(5)
    }
}
