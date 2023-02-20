package lotto.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoCountTest {
    @Test
    fun `구입금액이 13000원이고 로또 가격이 1000원인데 수동으로 14개를 구매하려고 하면 에러 발생`() {
        // given
        val purchaseMoney = PurchaseMoney(13000)
        val lottoManualCount = 14
        val except = "구매할 로또의 수가 구매한 수량보다 많습니다. 구매 할 수 : %d".format(lottoManualCount)

        // when
        val thrown = assertThrows<IllegalArgumentException> { LottoCount.from(lottoManualCount, purchaseMoney) }

        // then
        Assertions.assertThat(thrown.message).isEqualTo(except)
    }
}
