import domain.Money
import domain.Purchaser
import domain.WinStatistics
import domain.WinningNumbers
import domain.lotto.Lotto
import domain.lotto.LottoNumber
import view.InputView
import view.OutputView
import view.UI

class LottoController {

    fun runLottoGame() {
        val purchaser = Purchaser(getMoney())
        val winStatistics = WinStatistics(getWinningNumbers(), purchaser.purchasedLottoBundle)
        printOutputView(purchaser, winStatistics)
    }

    private fun printOutputView(purchaser: Purchaser, winStatistics: WinStatistics) {
        OutputView.printPurchasedLottoCount(purchaser.numberOfPurchasedLotto)
        OutputView.printPurchasedLotto(purchaser.purchasedLottoBundle)
        OutputView.printWinStatistics(winStatistics)
        OutputView.printEarningRate(winStatistics.calculateEarningRate(purchaser.purchasedMoney))
    }

    private fun getWinningNumbers(): WinningNumbers {
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun getMoney(): Money {
        UI.printRequestMoney()
        val money = InputView.inputMoney()
        return Money(money)
    }

    private fun getWinningLotto(): Lotto {
        UI.printRequestWinningNumbers()
        val winningNumbers = InputView.inputWinningNumbers()
        return Lotto(winningNumbers)
    }

    private fun getBonusNumber(): LottoNumber {
        UI.printRequestBonusNumber()
        val bonusNumber = InputView.inputBonusNumber()
        return LottoNumber.of(bonusNumber)
    }
}
