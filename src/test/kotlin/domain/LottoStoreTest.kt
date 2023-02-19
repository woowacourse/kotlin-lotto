package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoStoreTest {
    @ValueSource(ints = [1000, 100000])
    @ParameterizedTest
    fun `구매금액에 로또 가격으로 나눈만큼 로또를 반환할 수 있다`(int: Int) {
        val money = Money.from(int)
        val lotto: List<Lotto> = LottoStore(RandomLottoGenerator).buyLotto(money)
        assertThat(lotto.size).isEqualTo(money.value / 1000)
    }
}
