package domaintest.lottogeneratortest

import domain.lottogenerator.WinningLottoGenerator
import domain.model.WinningLotto
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningLottoGeneratorTest {

    @Test
    fun `1등 로또를 생성하기`() {
        val winningLotto = WinningLottoGenerator().generateWinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)

        assertThat(winningLotto).isInstanceOf(WinningLotto::class.java)
    }
}
