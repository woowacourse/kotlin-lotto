package controller

import domain.game.LottoGame
import domain.game.LottoMachine
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
) {
    fun startLottoGame() {
        val (lottoGame, purchasedLottos, money) = initLottoGame()
        matchLottos(lottoGame, purchasedLottos, money)
    }

    private fun initLottoGame(): Triple<LottoGame, List<PurchasedLotto>, Money> {
        val (money, purchasedLottos) = purchaseLotto()
        val (bonusNumber, winningLotto) = inputWinnings()
        val lottoGame = LottoGame(winningLotto, bonusNumber)
        return Triple(lottoGame, purchasedLottos, money)
    }

    private fun purchaseLotto(): Pair<Money, List<PurchasedLotto>> {
        val money = inputPurchasingMoney()
        val manualLottoSize = inputPurchasingManualLottoSize()
        val manualLottoNumbers = inputManualLottoNumbers(manualLottoSize)
        val purchasedLottos = purchaseLottos(money)
        printPurchasedLotto(purchasedLottos)
        return Pair(money, purchasedLottos)
    }

    private fun inputPurchasingMoney(): Money = inputView.inputPurchasingMoney()

    private fun inputPurchasingManualLottoSize(): LottoSize = inputView.inputPurchasingManualLottoSize()

    private fun inputManualLottoNumbers(size: LottoSize): List<List<String>> = inputView.inputManualLottoNumbers(size)

    private fun inputWinnings(): Pair<LottoNumber, WinningLotto> {
        val winningLottoNumbers = inputLastWeekWinningNumbers()
        val bonusNumber = inputBonusNumber()
        val winningLotto = WinningLotto(winningLottoNumbers, bonusNumber)
        return Pair(bonusNumber, winningLotto)
    }

    private fun purchaseLottos(purchasedMoney: Money): List<PurchasedLotto> =
        LottoMachine().purchaseAutoLottos(purchasedMoney)

    private fun inputLastWeekWinningNumbers(): List<LottoNumber> =
        inputView.inputLastWeekWinningNumbers().map { LottoNumber.from(it) }

    private fun inputBonusNumber(): LottoNumber = inputView.inputBonusBallNumber()

    private fun printPurchasedLotto(purchasedLottos: List<PurchasedLotto>) {
        resultView.printPurchasedLottos(purchasedLottos)
    }

    private fun matchLottos(lottoGame: LottoGame, purchasedLottos: List<PurchasedLotto>, purchasedMoney: Money) {
        val matchResult = lottoGame.matchLottos(purchasedLottos)
        resultView.printIncomeStatics(matchResult, lottoGame.calculateIncomeRate(matchResult, purchasedMoney))
    }
}
