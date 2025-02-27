package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoSellerTest {
    @Test
    fun `로또 수량에 맞게 로또를 발행해 반환한다`() {
        val price = 10000
        val manualLottoAmount = 3
        val manualLottoNumbers =
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }),
                Lotto(listOf(7, 8, 9, 10, 11, 12).map { LottoNumber(it) }),
                Lotto(listOf(13, 14, 15, 16, 17, 18).map { LottoNumber(it) }),
            )

        assertThat(LottoSeller(price, manualLottoAmount, manualLottoNumbers).getLottos().size).isEqualTo(10)
    }
}
