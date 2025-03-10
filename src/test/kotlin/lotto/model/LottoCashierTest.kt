package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoCashierTest {
    private val lottoCashier = LottoCashier()

    @Test
    fun `정상적으로 로또를 구매하면 올바른 개수의 로또가 생성된다`() {
        // given
        val purchaseAmount = Amount(10000)
        val manualLottoNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
            )

        // when
        val lottoWallet = lottoCashier.buyLottos(purchaseAmount, manualLottoNumbers)

        // then
        assertThat(lottoWallet.lottos.size).isEqualTo(10)
    }

    @Test
    fun `10000원을 지불하고 수동 로또를 입력하지 않으면 10개의 자동 로또가 반환된다`() {
        // given
        val purchaseAmount = Amount(10000)
        val manualLottoNumbers = emptyList<List<Int>>()

        // when
        val lottoWallet = lottoCashier.buyLottos(purchaseAmount, manualLottoNumbers)

        // then
        assertThat(lottoWallet.lottos.size).isEqualTo(10)
    }
}
