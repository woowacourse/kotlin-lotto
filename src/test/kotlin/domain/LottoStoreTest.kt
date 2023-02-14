package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoStoreTest {
    @ValueSource(ints = [1000, 100000])
    @ParameterizedTest
    fun `구매금액이 1000원 이상 100001원 이하면 개수만큼 로또를 반환할 수 있다`(amount: Int) {
        val lottoStore = LottoStore()
        val lotto: List<Lotto> = lottoStore.buyLotto(amount)
        assertThat(lotto.size).isEqualTo(amount / 1000)
    }

    @ValueSource(ints = [-1, 100001])
    @ParameterizedTest
    fun `구매금액이 1000원 미만이거나 100001원 이상이면 에러가 발생한다`(amount: Int) {
        val lottoStore = LottoStore()
        assertThatIllegalArgumentException()
            .isThrownBy { lottoStore.buyLotto(amount) }
            .withMessage("구매 할 수 있는 금액은 1000원 이상 100000원 이하입니다.\n잘못된 값: $amount")
    }
}
