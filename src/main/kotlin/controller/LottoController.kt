package controller

import domain.Lotto
import domain.LottoGame
import domain.LottoMachine
import domain.LottoNumber
import domain.Money
import domain.RandomLottoGenerator
import domain.WinningLotto
import domain.WinningResult
import view.InputView
import view.OutputView

class LottoController(val inputView: InputView, val outputView: OutputView) {

    fun run() {
        val money = runCatching {
            Money(inputView.inputMoney())
        }.onFailure { outputView.outputErrorMessage(it.message!!) }.getOrThrow()
        outputView.outputLottoSizeMessage(money)
        startGame(money)
    }

    private fun startGame(money: Money) {
        val lottos = LottoMachine(RandomLottoGenerator()).generateLottos(money)
        outputView.outputLottos(lottos)
        val winningLotto = runCatching {
            WinningLotto(Lotto(inputView.inputWinningLotto()), LottoNumber.from(inputView.inputBonusNumber()))
        }.onFailure { outputView.outputErrorMessage(it.message!!) }.getOrThrow()
        endGame(lottos, winningLotto, money)
    }

    private fun endGame(lottos: List<Lotto>, winningLotto: WinningLotto, money: Money) {
        val winningResult = WinningResult(LottoGame(lottos, winningLotto).matchGame())
        outputView.outputWinningResult(winningResult)
        outputView.outputYield(winningResult.calculateYield(money))
    }
}
