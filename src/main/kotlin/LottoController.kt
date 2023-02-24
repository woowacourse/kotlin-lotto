import domain.Money
import domain.Purchaser
import domain.WinStatistics
import domain.WinningNumbers
import domain.lotto.Lotto
import domain.lotto.LottoBundle
import domain.lotto.LottoNumber
import view.InputView
import view.ResultOutputView

class LottoController {

    fun runLottoGame() {
        val purchaser = Purchaser(getMoney(), getManualLottoCount())
        purchaser.purchaseManualLottoBundle(getManualLottoBundle(purchaser.manualLottoCount))
        ResultOutputView.printPurchasingResult(purchaser)
        val winStatistics = WinStatistics(getWinningNumbers(), purchaser.purchasedLottoBundle, purchaser.spentMoney)
        ResultOutputView.printWinStatistics(winStatistics)
    }

    private fun getMoney(): Money {
        return Money(InputView.getInputMoney())
    }

    private fun getWinningNumbers(): WinningNumbers {
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun getWinningLotto(): Lotto {
        return Lotto(InputView.getInputWinningLotto())
    }

    private fun getBonusNumber(): LottoNumber {
        return LottoNumber.of(InputView.getInputBonusNumber())
    }

    private fun getManualLottoCount(): Int {
        return InputView.getInputManualLottoCount()
    }

    private fun getManualLottoBundle(manualLottoCount: Int): LottoBundle {
        val manualLottos = InputView.getInputManualLottos(manualLottoCount)
            .map { Lotto(it) }
        return LottoBundle(manualLottos)
    }
}
