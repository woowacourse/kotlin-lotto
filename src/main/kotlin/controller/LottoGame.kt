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
    fun initGame() {
        output.outputMoneyMessage()
        money = input.inputMoney()
        output.outputLottoSizeMessage(money)
        lottos = RandomLottoGenerator().generateLottos(money)
        output.outputLottos(lottos)
    }

    fun startGame() {
        output.outputWinningLottoMessage()
        val winningLotto = input.inputWinningLotto()
        output.outputBonusNumberMessage()
        val bonusNumber = input.inputBonusNumber()
        winningResult = lottos.matchLottos(winningLotto, bonusNumber)
    }

    fun endGame() {
        output.outputWinningResult(winningResult)
        output.outputYield(winningResult.calculateYield(money))
    }
}
