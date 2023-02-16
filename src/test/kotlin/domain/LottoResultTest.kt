package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class gLottoResultTest {
    @Test
    fun `로또 결과를 정상적으로 저장할 수 있다`() {
        val lottos = listOf(
            Lotto.create(listOf(1, 2, 3, 4, 5, 6)), // 1등
            Lotto.create(listOf(2, 3, 4, 5, 6, 7)), // 2등
            Lotto.create(listOf(3, 4, 5, 6, 7, 8)), // 4등
        )
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val result = LottoResult.of(lottos, winningLotto)

        val expect = LottoResult(
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 0,
                Rank.FOURTH to 1,
                Rank.FIFTH to 0,
                Rank.MISS to 0,
            ),
        )

        assertThat(result).isEqualTo(expect)
    }

    @Test
    fun `로또 수익률을 반환한다`() {
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
        assertThat(lottoResult.getRateOfReturn()).isEqualTo((Rank.FIRST.winningMoney.toDouble() + Rank.SECOND.winningMoney + Rank.FOURTH.winningMoney) / (lottoResult.values.sum() * LottoStore.LOTTO_PRICE))
    }

    @Test
    fun `로또가 하나도 안들어오면 오류가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            LottoResult(
                mapOf(
                    Rank.FIRST to 0,
                    Rank.SECOND to 0,
                    Rank.THIRD to 0,
                    Rank.FOURTH to 0,
                    Rank.FIFTH to 0,
                    Rank.MISS to 0,
                ),
            )
        }.withMessage("적어도 하나 이상의 복권을 넣어야합니다.")
    }
}
