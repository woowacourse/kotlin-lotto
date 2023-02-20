import domain.Money
import domain.Purchaser
import domain.WinStatistics
import domain.WinningNumbers
import domain.lotto.Lotto
import domain.lotto.LottoNumber
import view.InputRequestView
import view.InputView
import view.ResultOutputView

class LottoController {

    fun runLottoGame() {
        val purchaser = Purchaser(getMoney())
        ResultOutputView.printPurchasingResult(purchaser)
        val winStatistics = WinStatistics(getWinningNumbers(), purchaser.purchasedLottoBundle, purchaser.spentMoney)
        ResultOutputView.printWinStatistics(winStatistics)
    }

    private fun getWinningNumbers(): WinningNumbers {
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun getMoney(): Money {
        InputRequestView.printRequestMoney()
        val money = InputView.inputMoney()
        return Money(money)
    }

    private fun getWinningLotto(): Lotto {
        InputRequestView.printRequestWinningNumbers()
        val winningNumbers = InputView.inputWinningNumbers()
        return Lotto(winningNumbers)
    }

    private fun getBonusNumber(): LottoNumber {
        InputRequestView.printRequestBonusNumber()
        val bonusNumber = InputView.inputBonusNumber()
        return LottoNumber.of(bonusNumber)
    }
}
