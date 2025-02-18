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
            require(price >= Lotto.LOTTO_PRICE) { ERROR_MESSAGE_NOT_ENOUGH_MONEY }
            val count = price / Lotto.LOTTO_PRICE
            require(count == lottos.size) { ERROR_MESSAGE_NOT_MATCHED_MONEY_AND_LOTTOS_COUNT }
            return Lottos(*lottos)
        }

        private const val ERROR_MESSAGE_NOT_ENOUGH_MONEY = "최소 구입 금액은 ${Lotto.LOTTO_PRICE}원입니다."
        private const val ERROR_MESSAGE_NOT_MATCHED_MONEY_AND_LOTTOS_COUNT = "구입 금액과 로또의 개수가 일치하지 않습니다."
    }
}

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
