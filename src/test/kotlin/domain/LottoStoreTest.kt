package domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStoreTest {
    private val lottoStore = LottoStore(RandomLottoGenerator)

    @Test
    fun `수동 로또를 구매한다`() {
        val money = Money.from(1000)
        val lotto: List<Lotto> = lottoStore.buyManualLotto(money, Lotto(1, 2, 3, 4, 5, 6))
        assertThat(lotto.size).isEqualTo(1)
    }

    @Test
    fun `자동 로또를 구매한다`() {
        val money = Money.from(10000)
        val lotto: List<Lotto> = lottoStore.buyAutoLotto(money)
        assertThat(lotto.size).isEqualTo(10)
    }

    @Test
    fun `구입 금액보다 많은 수동 로또를 구매하려고 한다`() {
        val money = Money.from(1000)
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { lottoStore.buyManualLotto(money, Lotto(1, 2, 3, 4, 5, 6), Lotto(1, 2, 3, 4, 5, 6)) }
            .withMessage("구입 금액보다 많은 로또를 구매할 수 없습니다.\n잘못된 값: 1000")
    }

    @Test
    fun `구입 금액보다 적은 자동 로또를 구매하려고 한다`() {
        val money = Money.from(999)
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { lottoStore.buyAutoLotto(money) }
            .withMessage("최소 금액보다 적은 돈으로 로또를 구매할 수 없습니다.\n잘못된 값: 999")
    }
}
