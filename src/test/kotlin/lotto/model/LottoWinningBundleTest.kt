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
        val winningLotto = Lotto((1..6).map { LottoNumber.of(it) })

        assertThrows<IllegalArgumentException> {
            LottoWinningBundle(winningLotto, LottoNumber.of(bonusNumber))
        }
    }

    @Test
    fun `5개의 당첨 번호가 일치하고 보너스 번호가 일치하지 않으면 3등이고, 일치하면 2등이다`() {
        val lottos =
            listOf(
                Lotto((1..6).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 8).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber.of(it) })
        val lottoWinningBundle = LottoWinningBundle(winningLotto, LottoNumber.of(8))
        val lottoResult = lottoWinningBundle.calculateResult(lottos)
        assertThat(lottoResult).isEqualTo(LottoResult(mapOf(LottoRank.THIRD to 1, LottoRank.SECOND to 1)))
    }

    @Test
    fun `6개의 당첨 번호가 일치하면 1등이고, 4개의 당첨번호가 일치하면 4등이다`() {
        val lottos =
            listOf(
                Lotto((1..6).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 3, 4, 7, 8).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val lottoWinningBundle = LottoWinningBundle(winningLotto, LottoNumber.of(8))
        val lottoResult = lottoWinningBundle.calculateResult(lottos)
        assertThat(lottoResult).isEqualTo(LottoResult(mapOf(LottoRank.FIRST to 1, LottoRank.FOURTH to 1)))
    }
}
