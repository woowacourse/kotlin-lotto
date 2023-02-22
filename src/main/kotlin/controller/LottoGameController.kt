package controller

import domain.game.LottoGame
import domain.game.LottoMachine
import domain.lotto.Lotto
import domain.lotto.PurchasedLotto
import domain.lotto.WinningLotto
import domain.lotto.number.LottoNumber
import domain.lotto.size.LottoSize
import domain.money.Money
import view.InputView
import view.ResultView

class LottoGameController(
    private val inputView: InputView = InputView(),
    private val resultView: ResultView = ResultView(),
    private val lottoMachine: LottoMachine = LottoMachine(),
) {
    fun startLottoGame() {
        val money = inputPurchasingMoney()
        val (change, manualLottos) = purchaseManualLottos(money)
        val autoLottos = purchaseAutoLottos(change)
        printPurchasedLotto(manualLottos = manualLottos, autoLottos = autoLottos)

        val winningLottoNumbers = inputLastWeekWinningNumbers()
        val bonusNumber = inputBonusNumber()
        val winningLotto = WinningLotto(Lotto(winningLottoNumbers), bonusNumber)
        val lottoGame = LottoGame(winningLotto, bonusNumber)
        matchLottos(lottoGame, autoLottos + manualLottos, money)
    }

    private fun purchaseAutoLottos(money: Money): List<PurchasedLotto> = LottoMachine().purchaseAutoLottos(money)

    private fun purchaseManualLottos(money: Money): Pair<Money, List<PurchasedLotto>> {
        val manualLottoSize = inputPurchasingManualLottoSize()
        val manualLottoNumbers = inputManualLottoNumbers(manualLottoSize)
        return lottoMachine.purchaseManualLottos(money, manualLottoSize, manualLottoNumbers)
    }

    private fun inputPurchasingMoney(): Money = inputView.inputPurchasingMoney()

    private fun inputPurchasingManualLottoSize(): LottoSize = inputView.inputPurchasingManualLottoSize()

    private fun inputManualLottoNumbers(size: LottoSize): List<Set<LottoNumber>> =
        inputView.inputManualLottoNumbers(size)

    private fun inputLastWeekWinningNumbers(): Set<LottoNumber> = inputView.inputLastWeekWinningNumbers()

    private fun inputBonusNumber(): LottoNumber = inputView.inputBonusBallNumber()

    private fun printPurchasedLotto(manualLottos: List<PurchasedLotto>, autoLottos: List<PurchasedLotto>) {
        resultView.printPurchasedLottos(manualLottos, autoLottos)
    }

    private fun matchLottos(lottoGame: LottoGame, purchasedLottos: List<PurchasedLotto>, purchasedMoney: Money) {
        val matchResult = lottoGame.matchLottos(purchasedLottos)
        resultView.printIncomeStatics(matchResult, lottoGame.calculateIncomeRate(matchResult, purchasedMoney))
    }
}
