package controller

import domain.game.LottoGame
import domain.game.LottoMachine
import domain.lotto.AutoLotto
import domain.lotto.ManualLotto
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
        val (autoLottos, manualLottos, money) = inputLottos()
        val (bonusNumber, winningLotto) = inputWinnings()
        val lottoGame = LottoGame(winningLotto, bonusNumber)
        matchLottos(lottoGame, autoLottos + manualLottos, money)
    }

    private fun inputLottos(): Triple<List<PurchasedLotto>, List<PurchasedLotto>, Money> {
        val money = inputPurchasingMoney()
        val (change, manualLottos) = purchaseManualLottos(money)
        val autoLottos = purchaseAutoLottos(change)

        printPurchasedLotto(manualLottos, autoLottos)
        return Triple(manualLottos, autoLottos, money)
    }

    private fun purchaseAutoLottos(money: Money): List<AutoLotto> = LottoMachine().purchaseAutoLottos(money)

    private fun purchaseManualLottos(money: Money): Pair<Money, List<ManualLotto>> {
        val manualLottoSize = inputPurchasingManualLottoSize()
        val manualLottoNumbers = inputManualLottoNumbers(manualLottoSize)
        return lottoMachine.purchaseManualLottos(money, manualLottoSize, manualLottoNumbers)
    }

    private fun inputPurchasingMoney(): Money = inputView.inputPurchasingMoney()

    private fun inputPurchasingManualLottoSize(): LottoSize = inputView.inputPurchasingManualLottoSize()

    private fun inputManualLottoNumbers(size: LottoSize): List<List<LottoNumber>> =
        inputView.inputManualLottoNumbers(size)

    private fun inputWinnings(): Pair<LottoNumber, WinningLotto> {
        val winningLottoNumbers = inputLastWeekWinningNumbers()
        val bonusNumber = inputBonusNumber()
        val winningLotto = WinningLotto(winningLottoNumbers, bonusNumber)
        return Pair(bonusNumber, winningLotto)
    }

    private fun inputLastWeekWinningNumbers(): List<LottoNumber> =
        inputView.inputLastWeekWinningNumbers().map { LottoNumber.from(it) }

    private fun inputBonusNumber(): LottoNumber = inputView.inputBonusBallNumber()

    private fun printPurchasedLotto(manualLottos: List<ManualLotto>, autoLottos: List<AutoLotto>) {
        resultView.printPurchasedLottos(manualLottos, autoLottos)
    }

    private fun matchLottos(lottoGame: LottoGame, purchasedLottos: List<PurchasedLotto>, purchasedMoney: Money) {
        val matchResult = lottoGame.matchLottos(purchasedLottos)
        resultView.printIncomeStatics(matchResult, lottoGame.calculateIncomeRate(matchResult, purchasedMoney))
    }
}
