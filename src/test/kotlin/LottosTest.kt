package lotto

import TestNumberGenerator
import lotto.model.LottoStore
import model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `구입 금액만큼 로또를 생성하는지 테스트`() {
        val lottoStore = LottoStore(1, TestNumberGenerator())
        assertThat(lottoStore.lottos).isEqualTo(
            listOf(
                1, 2, 3, 4, 5, 6
            )
        )
    }
}
