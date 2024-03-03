package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Lottos
import lotto.model.WinningLotto
import lotto.model.WinningRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
    private fun setUpLotto(vararg numbers: Int): Lotto = Lotto.lottoNumbersOf(numbers.map { it.toString() })

    @Test
    fun `Lottos에 추가한 Lotto가 있는지 테스트`() {
        val lottos = Lottos()
        val testLotto1 = setUpLotto(1, 2, 3, 4, 5, 6)
        val testLotto2 = setUpLotto(11, 12, 13, 14, 15, 16)
        lottos.add(testLotto1)
        lottos.add(testLotto2)
        assertThat(lottos.lottos).usingRecursiveComparison().isEqualTo(
            listOf(testLotto1, testLotto2),
        )
    }

    @Test
    fun `결과 계산 테스트 - 1등`() {
        val lottos = Lottos()
        lottos.add(setUpLotto(1, 2, 3, 4, 5, 6))
        val winningLotto =
            WinningLotto(
                setUpLotto(1, 2, 3, 4, 5, 6),
                LottoNumber(10),
            )
        assertThat(
            winningLotto.getWinningResult(lottos),
        ).isEqualTo(mapOf(WinningRank.FIRST to 1))
    }

    @Test
    fun `결과 계산 테스트 - 2등`() {
        val lottos = Lottos()
        lottos.add(setUpLotto(1, 2, 3, 4, 5, 6))
        val winningLotto =
            WinningLotto(
                setUpLotto(1, 2, 3, 4, 5, 7),
                LottoNumber(6),
            )
        assertThat(
            winningLotto.getWinningResult(lottos),
        ).isEqualTo(mapOf(WinningRank.SECOND to 1))
    }

    @Test
    fun `결과 계산 테스트 - 3등`() {
        val lottos = Lottos()
        lottos.add(setUpLotto(1, 2, 3, 4, 5, 6))
        val winningLotto =
            WinningLotto(
                setUpLotto(1, 2, 3, 4, 5, 7),
                LottoNumber(10),
            )
        assertThat(
            winningLotto.getWinningResult(lottos),
        ).isEqualTo(mapOf(WinningRank.THIRD to 1))
    }

    @Test
    fun `결과 계산 테스트 - 4등`() {
        val lottos = Lottos()
        lottos.add(setUpLotto(1, 2, 3, 4, 5, 6))
        val winningLotto =
            WinningLotto(
                setUpLotto(1, 2, 3, 4, 15, 25),
                LottoNumber(10),
            )
        assertThat(
            winningLotto.getWinningResult(lottos),
        ).isEqualTo(mapOf(WinningRank.FOURTH to 1))
    }

    @Test
    fun `결과 계산 테스트 - 5등`() {
        val lottos = Lottos()
        lottos.add(setUpLotto(1, 2, 3, 4, 5, 6))
        val winningLotto =
            WinningLotto(
                setUpLotto(1, 2, 3, 14, 15, 17),
                LottoNumber(40),
            )
        assertThat(
            winningLotto.getWinningResult(lottos),
        ).isEqualTo(mapOf(WinningRank.FIFTH to 1))
    }

    @Test
    fun `결과 계산 테스트 - 꽝`() {
        val lottos = Lottos()
        lottos.add(setUpLotto(1, 2, 3, 4, 5, 6))
        val winningLotto =
            WinningLotto(
                setUpLotto(11, 22, 33, 34, 35, 37),
                LottoNumber(40),
            )
        assertThat(
            winningLotto.getWinningResult(lottos),
        ).isEqualTo(mapOf(WinningRank.NONE to 1))
    }
}
