package controller

import domain.Lotto
import domain.LottoStore
import domain.Money
import domain.RandomLottoGenerator
import domain.WinningLotto
import view.InputView
import view.OutputView

class LottoController {
    private val inputView by lazy { InputView() }
    private val outputView by lazy { OutputView() }

    fun run() {
        val lottos = runBuyLotto()
        val winningLotto = makeWinningLotto()
        calculateResult(winningLotto, lottos)
    }

    private fun runBuyLotto(): List<Lotto> {
        runCatching {
            val lottos = buyLotto(inputView.askPurchaseMoney())
            outputView.outputLottos(lottos)
            return lottos
        }.onFailure { outputView.outputError(it) }
        return runBuyLotto()
    }

    private fun buyLotto(money: Money): List<Lotto> {
        val store = LottoStore(RandomLottoGenerator)
        return store.buyLotto(money)
    }

    private fun makeWinningLotto(): WinningLotto {
        runCatching {
            return WinningLotto(inputView.askWinningNumbers(), inputView.askBonusNumber())
        }.onFailure { outputView.outputError(it) }
        return makeWinningLotto()
    }

    private fun calculateResult(winningLotto: WinningLotto, lottos: List<Lotto>) {
        runCatching {
            outputView.outputResult(winningLotto.match(lottos))
        }.onFailure { outputView.outputError(it) }
    }
}
