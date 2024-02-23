package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoAnalyzer
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.WinningBundle
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun start() {
        val lottos = buyLottos()
        val winningBundle = createWinningBundle()
        matchResult(lottos, winningBundle)
    }

    private fun buyLottos(): List<Lotto> {
        val price = InputView.readPrice()
        val lottoMachine = LottoMachine(price)
        val lottos = lottoMachine.createLottos()
        OutputView.printLottos(lottos)
        return lottos
    }

    private fun createWinningBundle(): WinningBundle {
        val winningNumbers = InputView.readWinningNumbers()
        val winningLotto = Lotto(winningNumbers.map { LottoNumber.of(it) })
        val bonusNumber = InputView.readBonusNumber()

        return WinningBundle(winningLotto, LottoNumber.of(bonusNumber))
    }

    private fun matchResult(
        lottos: List<Lotto>,
        winningBundle: WinningBundle,
    ) {
        val lottoResult = LottoAnalyzer.calculateResult(lottos, winningBundle)
        OutputView.printResult(lottoResult)
    }
}
