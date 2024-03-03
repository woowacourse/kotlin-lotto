package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoNumberGeneratorManager
import lotto.model.LottoStore
import lotto.model.WinningLottoCalculator
import lotto.model.WinningRank
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoStoreTest {
    @BeforeEach
    fun setupTestEnvironment() {
        LottoNumberGeneratorManager.generator = TestLottoNumberGenerator()
    }

    private fun setUpLotto(vararg numbers: String): Lotto = Lotto.lottoNumbersOf(numbers.map { it })

    @Test
    fun `자동 로또 결과 계산 테스트`() {
        val autoLottoCount = 3
        val lottoStore = LottoStore()
        lottoStore.generateLottos(emptyList(), autoLottoCount)
        val winningNumbers = setUpLotto("1", "2", "3", "4", "5", "6")
        val bonusNumber = LottoNumber(7)
        val winningLottoCalculator = WinningLottoCalculator(winningNumbers, bonusNumber)
        Assertions.assertThat(
            winningLottoCalculator.getWinningResult(lottoStore.lottos),
        ).isEqualTo(
            mapOf(
                WinningRank.FIRST to autoLottoCount,
            ),
        )
    }

    @Test
    fun `수동 로또 결과 계산 테스트`() {
        val autoLottoCount = 0
        val lottoStore = LottoStore()
        val manualLottos =
            listOf(
                setUpLotto("1", "2", "3", "4", "5", "6"),
                setUpLotto("1", "2", "3", "4", "5", "6"),
                setUpLotto("1", "2", "3", "4", "5", "6"),
            )
        lottoStore.generateLottos(manualLottos, autoLottoCount)
        val winningNumbers = setUpLotto("1", "2", "3", "4", "5", "6")
        val bonusNumber = LottoNumber(7)
        val winningLottoCalculator = WinningLottoCalculator(winningNumbers, bonusNumber)
        Assertions.assertThat(
            winningLottoCalculator.getWinningResult(lottoStore.lottos),
        ).isEqualTo(
            mapOf(
                WinningRank.FIRST to manualLottos.size,
            ),
        )
    }
}
