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
    lateinit var money: Money
    lateinit var lottos: Lottos
    lateinit var winningResult: WinningResult

    fun run() {
        initGame()
        startGame()
        endGame()
    }
    private fun initGame() {
        money = input.inputMoney()
        output.outputLottoSizeMessage(money)
        lottos = RandomLottoGenerator().generateLottos(money)
        output.outputLottos(lottos)
    }

    private fun startGame() {
        val winningLotto = input.inputWinningLotto()
        val bonusNumber = input.inputBonusNumber()
        winningResult = lottos.matchLottos(winningLotto, bonusNumber)
    }

    private fun endGame() {
        output.outputWinningResult(winningResult)
        output.outputYield(winningResult.calculateYield(money))
    }
}
