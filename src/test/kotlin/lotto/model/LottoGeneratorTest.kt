package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoPrice
import lotto.entity.Lottos
import lotto.entity.PurchaseMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LottoGeneratorTest {
    @Test
    fun `구입 금액이 3000원이고, 로또 가격이 1000원일 때, 로또를 3개 생성한다`() {
        val lottoGenerator = SequentialLottoNumberGenerator(listOf(Lotto(listOf()), Lotto(listOf()), Lotto(listOf())))
        val lottos = Lottos(PurchaseMoney(3000), LottoPrice(1000), lottoGenerator)
        assertThat(lottos.value.size).isEqualTo(3)
    }
}
