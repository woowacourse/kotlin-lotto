package lotto

import lotto.model.LottoNumberGenerator
import lotto.model.LottoNumbers
import lotto.model.Lottos
import lotto.model.WinningRank
import model.Lotto
import model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `Lottos에 추가한 Lotto가 있는지 테스트`() {
        val lottos = Lottos()
        val lottoNumberGenerator = LottoNumberGenerator()
        val testLotto1 = Lotto(lottoNumberGenerator.generate())
        val testLotto2 = Lotto(lottoNumberGenerator.generate())
        lottos.add(testLotto1)
        lottos.add(testLotto2)
        assertThat(lottos.lottos).usingRecursiveComparison().isEqualTo(
            listOf(testLotto1, testLotto2)
        )
    }

    @Test
    fun `결과 계산 테스트 - 1등`() {
        val lottos = Lottos()
        lottos.add(Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 6)))
        assertThat(
            lottos.winningResult(
                Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 6)),
                LottoNumber(10)
            )
        ).isEqualTo(mapOf(WinningRank.FIRST to 1))
    }

    @Test
    fun `결과 계산 테스트 - 2등`() {
        val lottos = Lottos()
        lottos.add(Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 6)))
        assertThat(
            lottos.winningResult(
                Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 7)),
                LottoNumber(6)
            )
        ).isEqualTo(mapOf(WinningRank.SECOND to 1))
    }

    @Test
    fun `결과 계산 테스트 - 3등`() {
        val lottos = Lottos()
        lottos.add(Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 6)))
        assertThat(
            lottos.winningResult(
                Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 7)),
                LottoNumber(10)
            )
        ).isEqualTo(mapOf(WinningRank.THIRD to 1))
    }

    @Test
    fun `결과 계산 테스트 - 4등`() {
        val lottos = Lottos()
        lottos.add(Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 6)))
        assertThat(
            lottos.winningResult(
                Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 15, 25)),
                LottoNumber(10)
            )
        ).isEqualTo(mapOf(WinningRank.FOURTH to 1))
    }

    @Test
    fun `결과 계산 테스트 - 5등`() {
        val lottos = Lottos()
        lottos.add(Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 6)))
        assertThat(
            lottos.winningResult(
                Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 14, 15, 17)),
                LottoNumber(40)
            )
        ).isEqualTo(mapOf(WinningRank.FIFTH to 1))
    }

    @Test
    fun `결과 계산 테스트 - 꽝`() {
        val lottos = Lottos()
        lottos.add(Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 6)))
        assertThat(
            lottos.winningResult(
                Lotto(LottoNumbers.lottoNumbersOf(11, 22, 33, 34, 35, 37)),
                LottoNumber(17)
            )
        ).isEqualTo(mapOf(WinningRank.NONE to 1))
    }
}
