package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoMarketTest {
    private val manualLottoMachine = ManualLottoMachine()
    private val autoLottoMachine = AutoLottoMachine()

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
        val lottoMarket = LottoMarket(purchaseAmount, manualLottoNumbers)
        val lottoWallet = lottoMarket.buy(manualLottoMachine, autoLottoMachine)

        // then
        assertThat(lottoWallet.lottos.size).isEqualTo(10)
    }

    @Test
    fun `10000원을 지불하고 수동 로또를 입력하지 않으면 10개의 자동 로또가 반환된다`() {
        // given
        val purchaseAmount = Amount(10000)
        val manualLottoNumbers = emptyList<List<Int>>()

        // when
        val lottoMarket = LottoMarket(purchaseAmount, manualLottoNumbers)
        val lottoWallet = lottoMarket.buy(manualLottoMachine, autoLottoMachine)

        // then
        assertThat(lottoWallet.lottos.size).isEqualTo(10)
    }

    @Test
    fun `구입 금액보다 많은 수동 로또를 요구하면 오류를 반환한다`() {
        // given
        val purchaseAmount = Amount(1000)
        val manualLottoNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
            )

        // when & then
        assertThrows<IllegalArgumentException> {
            LottoMarket(purchaseAmount, manualLottoNumbers)
        }
    }

    @Test
    fun `입력한 금액은 0원 초과만 가능하다`() {
        // given
        val amount = 0

        // when & then
        assertThatThrownBy {
            LottoMarket(Amount(amount), emptyList())
        }.hasMessageContaining("0원 이상의 금액")
    }

    @Test
    fun `입력한 금액이 1,000으로 나누어지지 않으면 실패한다`() {
        // given
        val amount = 1001

        // when & then
        assertThatThrownBy {
            LottoMarket(Amount(amount), emptyList())
        }.hasMessageContaining("단위")
    }

    @Test
    fun `구입 금액이 5,000원이면 로또 구입 개수를 5개로 반환한다`() {
        // given
        val purchaseAmount = Amount(5000)

        // when
        val lottoMarket = LottoMarket(purchaseAmount, emptyList())
        val lottoWallet = lottoMarket.buy(manualLottoMachine, autoLottoMachine)

        // then
        assertThat(lottoWallet.lottos.size).isEqualTo(5)
    }
}
