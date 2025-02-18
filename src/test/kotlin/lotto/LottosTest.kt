package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Lottos(
    vararg lottos: Lotto,
) {
    val lottos: Set<Lotto> = lottos.toSet()

    companion object {
        fun buy(
            price: Int,
            vararg lottos: Lotto,
        ): Lottos {
            require(price >= Lotto.PRICE) { "최소 구입 금액은 ${Lotto.PRICE}원입니다." }
            val count = price / Lotto.PRICE
            require(count == lottos.size) { "구입 금액과 로또의 개수가 일치하지 않습니다." }
            return Lottos(*lottos)
        }
    }
}

class LottosTest {
    @Test
    fun `구입금액만큼 로또를 생성한다`() {
        val wantLottoCount = 5
        val lottos: Lottos =
            Lottos.buy(
                Lotto.PRICE * wantLottoCount,
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 6),
            )
        assertThat(lottos.lottos.size).isEqualTo(wantLottoCount)
    }
}
