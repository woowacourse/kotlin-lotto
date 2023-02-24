package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class LottoResultTest {
    @Test
    fun `로또들과 당첨 로또로 로또 결과를 생성할 수 있다`() {
        val lottos = listOf(
            Lotto.create(1, 2, 3, 4, 5, 6), // 1등
            Lotto.create(2, 3, 4, 5, 6, 7), // 2등
            Lotto.create(3, 4, 5, 6, 7, 8), // 4등
        )
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val result = LottoResult.of(lottos, winningLotto)

        assertAll(
            { assertThat(result[Rank.FIRST]).isEqualTo(1) },
            { assertThat(result[Rank.SECOND]).isEqualTo(1) },
            { assertThat(result[Rank.THIRD]).isEqualTo(0) },
            { assertThat(result[Rank.FOURTH]).isEqualTo(1) },
            { assertThat(result[Rank.FIFTH]).isEqualTo(0) },
            { assertThat(result[Rank.MISS]).isEqualTo(0) },
        )
    }

    @Test
    fun `당첨 결과에 대한 수익률을 계산할 수 있다`() {
        val lottoResult = LottoResult(
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 0,
                Rank.FOURTH to 1,
                Rank.FIFTH to 0,
                Rank.MISS to 0,
            ),
        )
        val expect =
            (Rank.FIRST.winningMoney.toDouble() + Rank.SECOND.winningMoney + Rank.FOURTH.winningMoney) / (3 * LottoStore.LOTTO_PRICE)
        assertThat(lottoResult.getRateOfReturn()).isEqualTo(expect)
    }
}
