package domaintest.lottogeneratortest

import domain.lottogenerator.ManualLottoGenerator
import domain.lottogenerator.WinningLottoGenerator
import domain.model.LottoResult
import domain.model.WinningLotto
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningLottoGeneratorTest {

    @Test
    fun `1등 로또를 생성하기`() {
        val winningLotto = WinningLottoGenerator().generateWinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)

        val lotto = ManualLottoGenerator().generate(listOf(1, 2, 3, 4, 5, 6))

        val actual =
            LottoResult.valueOf(lotto.getMatchCount(winningLotto.catchLotto), lotto.contains(winningLotto.bonusNumber))

        assertThat(actual).isEqualTo(LottoResult.FIRST)
    }
}
