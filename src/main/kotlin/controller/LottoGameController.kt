package controller

import domain.game.LottoGame
import domain.lotto.Lotto
import domain.lotto.PurchasedLotto
import domain.lotto.WinningLotto
import domain.lotto.number.LottoNumber
import domain.money.Money
import view.InputView
import view.ResultView

class LottoGameController(
    private val inputView: InputView,
    private val resultView: ResultView,
    private val lottoGame: LottoGame
) {

    fun startLottoGame() {
        val purchasedMoney = Money(inputView.inputPurchasingMoney())
        val manualLottoCount = inputView.inputManualLottoCount()
        val manualLottos = inputView.inputManualLottos(manualLottoCount)
            .map { numbers -> Lotto(makeLottoNumbers(numbers)) }
        val purchasedLottos = lottoGame.purchaseLottos(purchasedMoney, manualLottos)
        resultView.printPurchasedLottos(purchasedLottos)
        matchLottos(purchasedLottos, purchasedMoney)
    }

    private fun matchLottos(purchasedLottos: List<PurchasedLotto>, purchasedMoney: Money) {
        val winningNumbers = inputView.inputLastWeekWinningNumbers()
        val bonusNumber = inputView.inputBonusBallNumber()
        val winningLotto = WinningLotto(makeLottoNumbers(winningNumbers), LottoNumber(bonusNumber))
        val matchResult = lottoGame.matchLottos(purchasedLottos, winningLotto, LottoNumber(bonusNumber))
        resultView.printWinningRate(matchResult, lottoGame.calculateIncomeRate(matchResult, purchasedMoney))
    }

    private fun makeLottoNumbers(primitiveLotto: List<Int>): List<LottoNumber> {
        return primitiveLotto.map { LottoNumber(it) }
    }
}
