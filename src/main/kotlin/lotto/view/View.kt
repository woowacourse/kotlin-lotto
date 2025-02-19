package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoResult
import lotto.domain.Lottos
import lotto.domain.WinningLotto

object View {
    fun readPrice(): Int {
        OutputView.requestPrice()
        val price: Int = InputView.readPrice()
        val lottoCount: Int = price / Lotto.LOTTO_PRICE
        OutputView.showLottoCount(lottoCount)
        return price
    }

    fun showLottos(lottos: Lottos) {
        OutputView.showLottos(lottos)
    }

    fun showResult(
        lottoResults: List<LottoResult>,
        profitRate: Double,
    ) {
        OutputView.showResult(lottoResults, profitRate)
    }

    fun readWinningLotto(): WinningLotto {
        OutputView.requestWinningLotto()
        val winningLotto = InputView.readWinningNumbers()
        OutputView.requestBonusNumber()
        val bonusNUmber = InputView.readBonusNumber()
        return WinningLotto(winningLotto, bonusNUmber)
    }
}
