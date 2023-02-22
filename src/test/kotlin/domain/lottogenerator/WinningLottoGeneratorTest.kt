package domain.lottogenerator

import domain.model.LottoResult
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningLottoGeneratorTest {

    @Test
    fun `WinningLotto 로또를 생성하고 WinningLotto의 catchLotto가 다른 로또의 숫자 6개가 전부 일치할 경우 1등으로 판단`() {
        val winningLotto = WinningLottoGenerator().generateWinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)

        val lotto = ManualLottoGenerator().generate(listOf(1, 2, 3, 4, 5, 6))

        val actual =
            LottoResult.valueOf(lotto.getMatchCount(winningLotto.catchLotto), lotto.contains(winningLotto.bonusNumber))

        assertThat(actual).isEqualTo(LottoResult.FIRST)
    }
}
