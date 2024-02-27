package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoWinningBundleTest {
    @ParameterizedTest
    @ValueSource(ints = [6, 1])
    fun `로또 게임에서 보너스 번호는 당첨 번호와 중복되면 안된다`(bonusNumber: Int) {
        val winningLotto = Lotto(LottoNumbers((1..6).map { LottoNumber.of(it) }))

        assertThrows<IllegalArgumentException> {
            LottoWinningBundle(winningLotto, LottoNumber.of(bonusNumber))
        }
    }

    @Test
    fun `5개의 당첨 번호가 일치하고 보너스 번호가 일치하지 않으면 3등이고, 일치하면 2등이다`() {
        val buyedLottos =
            listOf(
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(1, 2, 3, 4, 5, 8),
            )
        val winningLotto = Lotto.of(1, 2, 3, 4, 5, 7)
        val lottoWinningBundle = LottoWinningBundle(winningLotto, LottoNumber.of(8))
        val lottoResult = lottoWinningBundle.calculateResult(buyedLottos)
        assertThat(lottoResult.winningCountsByLottoRank[LottoRank.THIRD]).isEqualTo(1)
        assertThat(lottoResult.winningCountsByLottoRank[LottoRank.SECOND]).isEqualTo(1)
    }

    @Test
    fun `6개의 당첨 번호가 일치하면 1등이고, 4개의 당첨번호가 일치하면 4등이다`() {
        val buyedLottos =
            listOf(
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(1, 2, 3, 4, 7, 8),
            )
        val winningLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val lottoWinningBundle = LottoWinningBundle(winningLotto, LottoNumber.of(8))
        val lottoResult = lottoWinningBundle.calculateResult(buyedLottos)
        assertThat(lottoResult.winningCountsByLottoRank[LottoRank.FIRST]).isEqualTo(1)
        assertThat(lottoResult.winningCountsByLottoRank[LottoRank.FOURTH]).isEqualTo(1)
    }
}
