package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoStore
import lotto.model.WinningLotto
import lotto.model.WinningRank
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoStoreTest {
    private fun setUpLotto(vararg numbers: String): Lotto = Lotto.lottoNumbersOf(numbers.map { it })

    @Test
    fun `자동 로또 결과 계산 테스트`() {
        val lottoCount = 3
        val lottoStore = LottoStore(TestLottoNumberGenerator())
        lottoStore.generateAutoLottos(lottoCount)
        val winningNumbers = setUpLotto("1", "2", "3", "4", "5", "6")
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        Assertions.assertThat(
            lottoStore.getWinningResult(winningLotto),
        ).isEqualTo(
            mapOf(
                WinningRank.FIRST to lottoCount,
            ),
        )
    }

    @Test
    fun `수동 로또 결과 계산 테스트`() {
        val lottoCount = 3
        val lottoStore = LottoStore(TestLottoNumberGenerator())
        val lottos =
            listOf(
                listOf("1", "2", "3", "4", "5", "6"),
                listOf("1", "2", "3", "4", "5", "6"),
                listOf("1", "2", "3", "4", "5", "6"),
            )
        lottoStore.generateManualLottos(lottos)
        val winningNumbers = setUpLotto("1", "2", "3", "4", "5", "6")
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        Assertions.assertThat(
            lottoStore.getWinningResult(winningLotto),
        ).isEqualTo(
            mapOf(
                WinningRank.FIRST to lottoCount,
            ),
        )
    }
}
