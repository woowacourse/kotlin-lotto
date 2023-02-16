package controller

import domain.game.LottoGame
import domain.game.LottoMachine
import domain.lotto.PurchasedLotto
import util.validator.InputValidator
import view.InputView
import view.ResultView

class LottoGameController {
    private val inputView = InputView(InputValidator())
    private val resultView = ResultView()
    private val lottoGame = LottoGame(LottoMachine())

    fun startLottoGame() {
        val purchasedMoney = inputView.inputPurchasingMoney()
        val purchasedLottos = lottoGame.purchaseLottos(purchasedMoney)
        resultView.printPurchasedLottos(purchasedLottos)
        initLottoGame()
        matchLottos(purchasedLottos, purchasedMoney)
    }

    private fun initLottoGame() {
        val winningNumbers = inputView.inputLastWeekWinningNumbers()
        val bonusNumber = inputView.inputBonusBallNumber()
        lottoGame.initWinningLottoNumbers(winningNumbers, bonusNumber)
    }

    private fun matchLottos(purchasedLottos: List<PurchasedLotto>, purchasedMoney: Int) {
        val matchResult = lottoGame.matchLottos(purchasedLottos)
        resultView.printWinningRate(matchResult, lottoGame.calculateIncomeRate(matchResult, purchasedMoney))
    }
}
