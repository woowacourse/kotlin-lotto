package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoVendingMachineTest {
    @ValueSource(ints = [1000, 5000, 10000])
    @ParameterizedTest
    fun `구입금액에 해당하는 로또 개수 계산`(amount: Int) {
        // given
        val money = Money.create(amount)

        // when
        val lottoCount: Int = LottoVendingMachine.getLottoCount(money)

        // then
        assertThat(lottoCount).isEqualTo(amount / LottoVendingMachine.LOTTO_PRICE)
    }

    @Test
    fun `로또 개수만큼 발행`() {
        // given
        val lottoCount: Int = 5
        val lotto = Lotto(
            setOf(
                LottoNumber.create(1),
                LottoNumber.create(2),
                LottoNumber.create(3),
                LottoNumber.create(4),
                LottoNumber.create(5),
                LottoNumber.create(6)
            )
        )
        // when
        val lottoBundle = LottoVendingMachine.getLottoBundle(lottoCount) {lotto}

        // then
        assertThat(lottoBundle.lottos.size).isEqualTo(lottoCount)
    }


}