package lotto

import lotto.model.LottoNumberGenerator
import lotto.model.LottoNumbers
import lotto.model.Lottos
import model.Lotto
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
}
