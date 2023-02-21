package domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoStoreTest {
    private val lottoStore = LottoStore(RandomLottoGenerator)

    @Test
    fun `원하는 번호들의 수동 로또들을 구매한다`() {
        val money = Money(2000)
        val lottoTickets: LottoTickets = lottoStore.buyManualLotto(money, intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(1, 11, 22, 33, 44, 45))
        assertThat(lottoTickets.size).isEqualTo(2)
        assertThat(lottoTickets[0].toList().map { it.number }).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
        assertThat(lottoTickets[1].toList().map { it.number }).isEqualTo(listOf(1, 11, 22, 33, 44, 45))
    }

    @Test
    fun `자동 로또들을 구매한다`() {
        val money = Money(100000)
        val lottoTickets: LottoTickets = lottoStore.buyAutoLotto(money)
        assertThat(lottoTickets.size).isEqualTo(100)
        lottoTickets.forEach { lotto ->
            assertThat(lotto.toList()).hasSize(6)
            lotto.toList().forEach { assertThat(it.number).isBetween(1, 45) }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 100])
    fun `1개 이상 100개 이하의 개수만큼 로또를 생성할 수 있다`(count: Int) {
        val lottoStore = LottoStore(RandomLottoGenerator)

        val result = lottoStore.createLottos(count)

        assertThat(result.size).isEqualTo(count)
    }

    @Test
    fun `구입 금액보다 많은 수동 로또를 구매하려 하면 에러가 발생한다`() {
        val money = Money(1000)
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { lottoStore.buyManualLotto(money, intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(1, 2, 3, 4, 5, 6)) }
            .withMessage("구입 금액보다 많은 로또를 구매할 수 없습니다.\n잘못된 값: 1000")
    }

    @Test
    fun `구입 금액보다 적은 자동 로또를 구매하려 하면 에러가 발생한다`() {
        val money = Money(999)
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { lottoStore.buyAutoLotto(money) }
            .withMessage("최소 금액보다 적은 돈으로 로또를 구매할 수 없습니다.\n잘못된 값: 999")
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 101])
    fun `로또를 생성할 때 생성 개수가 1개 이상 100개 이하가 아니면 에러가 발생한다`(count: Int) {
        val lottoStore = LottoStore(RandomLottoGenerator)

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { lottoStore.createLottos(count) }
            .withMessage("한 번에 생성할 수 있는 로또 개수는 1개 이상 100개 이하입니다.\n잘못된 값: $count")
    }
}
