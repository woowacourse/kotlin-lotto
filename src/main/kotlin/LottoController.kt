
import domain.LottoCount
import domain.Money
import domain.Purchaser
import domain.WinStatistics
import domain.WinningNumbers
import domain.lotto.Lotto
import domain.lotto.LottoBundle
import domain.lotto.LottoNumber
import domain.lotto.generator.LottoVendingMachine
import domain.lotto.generator.RandomLottoGenerator
import view.InputView
import view.ResultOutputView

class LottoController {

    fun runLottoGame() {
        val purchasedLottoBundle = refactoredPurchaseLotto() // purchaseLotto()
        val winStatistics = WinStatistics(getWinningNumbers(), purchasedLottoBundle)
        ResultOutputView.printWinStatistics(winStatistics)
    }

    private fun purchaseLotto(): LottoBundle {
        val purchaser = checkError { Purchaser(getMoney()) }
        checkError { purchaser.decideManualLottoCount(getManualLottoCount()) }
        checkError { purchaser.purchaseManualLottoBundle(getManualLottoBundle(purchaser.manualLottoCount)) }
        ResultOutputView.printPurchasingResult(purchaser)
        return purchaser.purchasedLottoBundle
    }

    private fun refactoredPurchaseLotto(): LottoBundle {
        val lottoCount = checkError { LottoCount(getMoney(), getManualLottoCount()) }
        ResultOutputView.printPurchasedLottoCount(lottoCount.totalCount)
        val manualLottoBundle = getManualLottoBundle(lottoCount.manualCount)
        val autoLottoBundle = LottoVendingMachine.getLottoBundle((lottoCount.autoCount), RandomLottoGenerator())
        val totalLottoBundle = manualLottoBundle + autoLottoBundle
        ResultOutputView.printPurchasedLotto(totalLottoBundle)
        return totalLottoBundle
    }

    private fun getMoney(): Money {
        return checkError { Money(InputView.getInputMoney()) }
    }

    private fun getWinningNumbers(): WinningNumbers {
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        return checkError { WinningNumbers(winningLotto, bonusNumber) }
    }

    private fun getWinningLotto(): Lotto {
        return checkError { Lotto(InputView.getInputWinningLotto()) }
    }

    private fun getBonusNumber(): LottoNumber {
        return checkError { LottoNumber.of(InputView.getInputBonusNumber()) }
    }

    private fun getManualLottoCount(): Int {
        return InputView.getInputManualLottoCount()
    }

    private fun getManualLottoBundle(manualLottoCount: Int): LottoBundle {
        val manualLottos = checkError {
            InputView.getInputManualLottos(manualLottoCount)
                .map { Lotto(it) }
        }
        return LottoBundle(manualLottos)
    }

    private fun <T> checkError(method: () -> T): T {
        return runCatching { method() }
            .getOrElse {
                println(it.message)
                return checkError(method)
            }
    }
}
