package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoStore
import lotto.model.WinningLotto
import lotto.model.WinningRank
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoStoreTest {
    private fun setUpLotto(vararg numbers: Int): Lotto = Lotto.lottoNumbersOf(numbers.toList())

    @Test
    fun `로또 결과 계산 테스트`() {
        val lottoStore = LottoStore(3, TestLottoNumberGenerator())
        val winningNumbers = setUpLotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        Assertions.assertThat(
            lottoStore.getWinningResult(winningLotto),
        ).isEqualTo(
            mapOf(
                WinningRank.FIRST to 3,
            ),
        )
    }
}
