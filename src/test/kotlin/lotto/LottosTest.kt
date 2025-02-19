package lotto

import lotto.domain.Lotto
import lotto.domain.Lottos
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `구입금액만큼 로또를 생성한다`() {
        val wantLottoCount = 5
        val lottos: Lottos =
            Lottos.buy(
                Lotto.LOTTO_PRICE * wantLottoCount,
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 6),
            )
        assertThat(lottos.lottos.size).isEqualTo(wantLottoCount)
    }
}
