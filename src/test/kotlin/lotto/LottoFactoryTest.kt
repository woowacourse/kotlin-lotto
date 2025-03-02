package lotto

import lotto.domain.AutoLottoGenerator
import lotto.domain.LottoFactory
import lotto.domain.ManualLottoGenerator
import lotto.domain.NumbersList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoFactoryTest {
    private val lottoFactory = LottoFactory()

    @Test
    fun `로또 개수에 따라 로또를 발급할 수 있다`() {
        val amountOfLotto = 10
        val autoGenerator = AutoLottoGenerator() // 자동 로또 생성기 사용
        val lottos = lottoFactory.generateLottos(amountOfLotto, autoGenerator)

        assertThat(lottos.size).isEqualTo(amountOfLotto)
    }

    @Test
    fun `수동 로또를 n개 발급할 수 있다`() {
        val manualLottoNumbers =
            mutableListOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(13, 14, 15, 16, 17, 18),
            )
        val numbersList = NumbersList(manualLottoNumbers)
        val manualGenerator = ManualLottoGenerator(numbersList)
        val expectedSize = numbersList.size()
        val lottos = lottoFactory.generateLottos(expectedSize, manualGenerator)

        assertThat(lottos.size).isEqualTo(expectedSize)
    }
}
