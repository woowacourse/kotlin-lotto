package controller

import domain.game.LottoGame
import domain.game.LottoMachine
import domain.lotto.PurchasedLotto
import domain.lotto.WinningLotto
import domain.lotto.number.LottoNumber
import domain.money.Money
import view.InputView
import view.ResultView

class LottoGameController(
    private val inputView: InputView = InputView(),
    private val resultView: ResultView = ResultView(),
) {
    fun startLottoGame() {
        val purchasedMoney = inputView.inputPurchasingMoney()
        val winningNumbers = inputView.inputLastWeekWinningNumbers().map { LottoNumber.from(it) }
        val bonusNumber = inputView.inputBonusBallNumber()
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        val lottoGame = LottoGame(winningLotto, bonusNumber, LottoMachine())
        val purchasedLottos = lottoGame.purchaseLottos(purchasedMoney)

        resultView.printPurchasedLottos(purchasedLottos)
        matchLottos(lottoGame, purchasedLottos, purchasedMoney)
    }

    private fun matchLottos(lottoGame: LottoGame, purchasedLottos: List<PurchasedLotto>, purchasedMoney: Money) {
        val matchResult = lottoGame.matchLottos(purchasedLottos)
        resultView.printIncomeStatics(matchResult, lottoGame.calculateIncomeRate(matchResult, purchasedMoney))
    }
}
