package lotto

import lotto.model.LottoNumbers
import lotto.model.LottoStore
import lotto.model.Lottos
import lotto.model.WinningNumbers
import lotto.model.WinningRank
import model.Lotto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoStoreTest {
    @Test
    fun `자동 로또 결과 계산 테스트`() {
        val winningNumbers = WinningNumbers.of(listOf("1", "2", "3", "4", "5", "6"), "7")
        Assertions.assertThat(
            LottoStore.generateAutoLottos(3, TestLottoNumberGenerator()).winningResult(winningNumbers),
        ).isEqualTo(mapOf(WinningRank.FIRST to 3))
    }

    @Test
    fun `수동 로또 결과 테스트`() {
        val winningNumbers = WinningNumbers.of(listOf("1", "2", "3", "4", "5", "6"), "7")
        Assertions.assertThat(
            LottoStore.generateManualLottos(listOf(Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 7))))
                .winningResult(winningNumbers),
        ).isEqualTo(mapOf(WinningRank.SECOND to 1))
    }

    @Test
    fun `자동 + 수동 로또 결과 테스트`() {
        val winningNumbers = WinningNumbers.of(listOf("1", "2", "3", "4", "5", "6"), "7")
        val manualLottos = LottoStore.generateManualLottos(listOf(Lotto(LottoNumbers.lottoNumbersOf(1, 2, 3, 4, 5, 7))))
        val autoLottos = LottoStore.generateAutoLottos(3, TestLottoNumberGenerator())
        val lottoBundle = Lottos(manualLottos.lottos + autoLottos.lottos)
        Assertions.assertThat(
            lottoBundle.winningResult(winningNumbers),
        ).isEqualTo(mapOf(WinningRank.FIRST to 3, WinningRank.SECOND to 1))
    }
}
