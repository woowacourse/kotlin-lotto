package controller

import domain.Lottos
import domain.Money
import domain.RandomLottoGenerator
import domain.WinningResult
import view.InputView
import view.OutputView

class LottoGame {

    private val input by lazy { InputView() }
    private val output by lazy { OutputView() }

    fun run() {
        val money = depositGame()
        val lottos = initGame(money)
        val winningResult = startGame(lottos)
        endGame(winningResult, money)
    }
    private fun depositGame(): Money {
        val money = input.inputMoney()
        output.outputLottoSizeMessage(money)
        return money
    }

    private fun initGame(money: Money): Lottos {
        val lottos = RandomLottoGenerator().generateLottos(money.lottoCount(money.price))
        output.outputLottos(lottos)
        return lottos
    }

    private fun startGame(lottos: Lottos): WinningResult {
        val winningLotto = input.inputWinningLotto()
        val bonusNumber = input.inputBonusNumber()
        return lottos.matchLottos(winningLotto, bonusNumber)
    }

    private fun endGame(winningResult: WinningResult, money: Money) {
        output.outputWinningResult(winningResult)
        output.outputYield(winningResult.calculateYield(money))
    }
}
