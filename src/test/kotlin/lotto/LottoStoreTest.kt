package lotto

import lotto.model.LottoNumbers
import lotto.model.LottoStore
import lotto.model.Lottos
import lotto.model.WinningRank
import model.Lotto
import model.LottoNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoStoreTest {
    private fun setUpLotto(vararg numbers: Int): Lotto = Lotto(LottoNumbers.lottoNumbersOf(*numbers))

    @Test
    fun `자동 로또 결과 계산 테스트`() {
        val winningNumbers = setUpLotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(7)
        Assertions.assertThat(
            LottoStore.generateAutoLottos(3, TestLottoNumberGenerator()).winningResult(winningNumbers, bonusNumber),
        ).isEqualTo(mapOf(WinningRank.FIRST to 3))
    }

    @Test
    fun `수동 로또 결과 테스트`() {
        val winningNumbers = setUpLotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(7)
        Assertions.assertThat(
            LottoStore.generateManualLottos(listOf(listOf("1", "2", "3", "4", "5", "7")))
                .winningResult(winningNumbers, bonusNumber),
        ).isEqualTo(mapOf(WinningRank.SECOND to 1))
    }

    @Test
    fun `자동 + 수동 로또 결과 테스트`() {
        val winningNumbers = setUpLotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(7)
        val manualLottos = LottoStore.generateManualLottos(listOf(listOf("1", "2", "3", "4", "5", "7")))
        val autoLottos = LottoStore.generateAutoLottos(3, TestLottoNumberGenerator())
        val lottoBundle = Lottos(manualLottos.lottos + autoLottos.lottos)
        Assertions.assertThat(
            lottoBundle.winningResult(winningNumbers, bonusNumber),
        ).isEqualTo(mapOf(WinningRank.FIRST to 3, WinningRank.SECOND to 1))
    }
}
