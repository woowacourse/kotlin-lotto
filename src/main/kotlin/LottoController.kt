import domain.Lotto
import domain.LottoBundle
import domain.LottoGenerator
import domain.LottoNumber
import domain.Payment
import domain.RandomLottoGenerator
import domain.WinningNumbers
import view.InputView
import view.OutputView
import view.UI

class LottoController {

    private val lottoGenerator: LottoGenerator = RandomLottoGenerator()

    fun startLottoGame() {
        val spendMoney = getMoney()
        val lottoCount = spendMoney.calculateLottoCount()
        OutputView.printPurchasedLottoCount(lottoCount)

        val lottoBundle = LottoBundle(lottoCount, lottoGenerator)
        OutputView.printPurchasedLotto(lottoBundle)

        produceResult(lottoBundle, spendMoney)
    }

    private fun getMoney(): Payment {
        UI.printRequestMoney()
        val money = InputView.inputMoney()
        return Payment(money)
    }

    private fun produceResult(lottoBundle: LottoBundle, spendPayment: Payment) {
        val winStatistics = getWinningNumbers().compareLottoBundle(lottoBundle)

        OutputView.printWinStatistics(winStatistics)
        OutputView.printEarningRate(winStatistics.calculateTotalIncome(), spendPayment)
    }

    private fun getWinningNumbers(): WinningNumbers {
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun getWinningLotto(): Lotto {
        UI.printRequestWinningNumbers()
        val winningNumbers : List<String> = InputView.inputWinningNumbers()
        return Lotto(winningNumbers)
    }

    private fun getBonusNumber(): LottoNumber {
        UI.printRequestBonusNumber()
        val bonusNumber = InputView.inputBonusNumber()
        return LottoNumber.of(bonusNumber)
    }
}
