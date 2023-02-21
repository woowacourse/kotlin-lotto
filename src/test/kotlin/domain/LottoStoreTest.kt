package domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class LottoStoreTest {
    private val lottoStore = LottoStore(RandomLottoGenerator)

    inner class TestLottoGenerator : LottoGenerator {
        private var lotateTickets = listOf(0, 1, 2, 3, 4, 5)
        override fun generateLotto(): Lotto {
            lotateTickets = lotateTickets.map { (it + 1) % 45 }
            return Lotto(*lotateTickets.toIntArray())
        }
    }

    @Test
    fun `원하는 번호들의 수동 로또들을 구매한다`() {
        val money = Money(2000)
        val lottoTickets: LottoTickets = lottoStore.buyManualLotto(money.count, intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(1, 11, 22, 33, 44, 45))
        assertAll({
            assertThat(lottoTickets.size).isEqualTo(2)
            assertThat(lottoTickets[0].toList().map { it.number }).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
            assertThat(lottoTickets[1].toList().map { it.number }).isEqualTo(listOf(1, 11, 22, 33, 44, 45))
        })
    }

    @Test
    fun `자동 로또들을 구매한다`() {
        val money = Money(2000)
        val lottoStore = LottoStore(TestLottoGenerator())
        val lottoTickets: LottoTickets = lottoStore.buyAutoLotto(money.count)
        assertAll({
            assertThat(lottoTickets.size).isEqualTo(2)
            assertThat(lottoTickets[0].toList().map { it.number }).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
            assertThat(lottoTickets[1].toList().map { it.number }).isEqualTo(listOf(2, 3, 4, 5, 6, 7))
        })
    }

    @Test
    fun `구입 금액보다 많은 수동 로또를 구매하려 하면 에러가 발생한다`() {
        val money = Money(1000)
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { lottoStore.buyManualLotto(money.count, intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(1, 2, 3, 4, 5, 6)) }
            .withMessage("잔액보다 많은 로또를 구매할 수 없습니다.\n잘못된 값: 1")
    }

    @Test
    fun `구입 금액보다 적은 자동 로또를 구매하려 하면 에러가 발생한다`() {
        val money = Money(999)
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { lottoStore.buyAutoLotto(money.count) }
            .withMessage("한 번에 생성할 수 있는 로또 개수는 1개 이상 100개 이하입니다.\n잘못된 값: 0")
    }
}
