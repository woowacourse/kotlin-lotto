import domain.Lotto
import domain.LottoBundle
import domain.LottoGenerator
import domain.LottoNumber
import domain.Payment
import domain.RandomLottoGenerator
import domain.WinningNumbers
import view.InputView
import view.OutputView

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
        while (true) {
            OutputView.printRequestMoney()
            val money = InputView.inputMoney()
            var payment: Payment? = null
            val result = kotlin.runCatching { payment = Payment(money) }
            if (result.isSuccess) return payment!!
            println(result.exceptionOrNull()?.message)
        }
    }

    private fun produceResult(lottoBundle: LottoBundle, spendPayment: Payment) {
        val winStatistics = getWinningNumbers().compareLottoBundle(lottoBundle)

        OutputView.printWinStatistics(winStatistics)
        OutputView.printEarningRate(winStatistics.calculateTotalIncome(), spendPayment)
    }

    private fun getWinningNumbers(): WinningNumbers {
        while (true) {
            val winningLotto = getWinningLotto()
            val bonusNumber = getBonusNumber()
            var winningNumbers: WinningNumbers? = null
            val result = kotlin.runCatching { winningNumbers = WinningNumbers(winningLotto, bonusNumber) }
            if (result.isSuccess) return winningNumbers!!
            println(result.exceptionOrNull()?.message)
        }
    }

    private fun getWinningLotto(): Lotto {
        while (true) {
            OutputView.printRequestWinningNumbers()
            val winningNumbers: List<String> = InputView.inputWinningNumbers()
            var lotto: Lotto? = null
            val result = kotlin.runCatching { lotto = Lotto(winningNumbers) }
            if (result.isSuccess) return lotto!!
            println(result.exceptionOrNull()?.message)
        }
    }

    private fun getBonusNumber(): LottoNumber {
        while (true) {
            OutputView.printRequestBonusNumber()
            val bonusNumber: String = InputView.inputBonusNumber()
            var lottoNumber: LottoNumber? = null
            val result = kotlin.runCatching { lottoNumber = LottoNumber.of(bonusNumber) }
            if (result.isSuccess) return lottoNumber!!
            println(result.exceptionOrNull()?.message)
        }
    }
}
