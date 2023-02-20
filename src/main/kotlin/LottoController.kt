import domain.Judgement
import domain.Lotto
import domain.LottoBundle
import domain.LottoGenerator
import domain.LottoNumber
import domain.LottoVendingMachine
import domain.Money
import domain.RandomLottoGenerator
import domain.WinStatistics
import domain.WinningNumbers
import view.InputView
import view.OutputView
import view.UI

class LottoController {

    private val lottoGenerator: LottoGenerator = RandomLottoGenerator()

    fun startLottoGame() {
        val spendMoney = getMoney()
        val lottoCount = LottoVendingMachine.getLottoCount(spendMoney)
        OutputView.printPurchasedLottoCount(lottoCount)
        val lottoBundle =
            LottoVendingMachine.generateLottoBundle(lottoCount = lottoCount, lottoGenerator = lottoGenerator)
        OutputView.printPurchasedLotto(lottoBundle)

        produceResult(lottoBundle, spendMoney)
    }

    private fun getMoney(): Money {
        UI.printRequestMoney()
        val money = InputView.inputMoney()
        return Money.create(money)
    }

    private fun produceResult(lottoBundle: LottoBundle, spendMoney: Money) {
        val winningResult = Judgement.compareLottoBundle(getWinningNumbers(), lottoBundle)
        val winStatistics = WinStatistics(winningResult)

        OutputView.printWinStatistics(winStatistics)
        OutputView.printEarningRate(winStatistics.getTotalIncome(), spendMoney)
    }

    private fun getWinningNumbers(): WinningNumbers {
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun getWinningLotto(): Lotto {
        UI.printRequestWinningNumbers()
        val winningNumbers = InputView.inputWinningNumbers()
        return Lotto(winningNumbers)
    }

    private fun getBonusNumber(): LottoNumber {
        UI.printRequestBonusNumber()
        val bonusNumber = InputView.inputBonusNumber()
        return LottoNumber.create(bonusNumber)
    }
}
