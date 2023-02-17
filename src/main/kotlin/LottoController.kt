import domain.Bank
import domain.Judgement
import domain.Money
import domain.WinningNumbers
import domain.lotto.Lotto
import domain.lotto.LottoBundleDto
import domain.lotto.LottoNumber
import domain.lotto.generator.LottoGenerator
import domain.lotto.generator.LottoVendingMachine
import domain.lotto.generator.RandomLottoGenerator
import view.InputView
import view.OutputView
import view.UI

class LottoController {

    private val lottoGenerator: LottoGenerator = RandomLottoGenerator()

    fun startLottoGame() {
        val spendMoney = getMoney()
        val lottoCount = LottoVendingMachine.getLottoCount(spendMoney)
        OutputView.printPurchasedLottoCount(lottoCount)
        val lottoBundle = LottoVendingMachine.getLottoBundle(lottoCount = lottoCount, lottoGenerator = lottoGenerator)
        OutputView.printPurchasedLotto(lottoBundle)

        produceResult(lottoBundle, spendMoney)
    }

    private fun produceResult(lottoBundleDto: LottoBundleDto, spendMoney: Money) {
        val winningResult = Judgement.compareLottoBundle(getWinningNumbers(), lottoBundleDto)
        val winStatistics = Bank.getWinStatistics(winningResult)

        OutputView.printWinStatistics(winStatistics)
        OutputView.printEarningRate(winStatistics.getTotalIncome(), spendMoney)
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
