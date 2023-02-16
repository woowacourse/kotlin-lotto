package lotto.model

import lotto.entity.LottoPrice
import lotto.entity.PurchaseMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameGeneratorTest {
    @Test
    fun `구입 금액이 3000원이고, 로또 가격이 1000원일 때, 로또를 3개 생성한다`() {
        val gameGenerator = GameGenerator(RandomLottoGenerator())
        assertThat(gameGenerator.generate(PurchaseMoney(3000), LottoPrice(1000)).value.size).isEqualTo(3)
    }
}
