import domain.Lotto
import domain.LottoBundle
import domain.LottoGenerator
import domain.LottoNumber
import domain.ManualCount
import domain.Payment
import domain.RandomLottoGenerator
import domain.WinningNumbers
import view.InputView
import view.OutputView

class LottoController {

    private val lottoGenerator: LottoGenerator = RandomLottoGenerator()

    fun startLottoGame() {
        val spendMoney: Payment = getMoney()
        val maxLottoCount: Int = spendMoney.calculateLottoCount()
        val manualCount: ManualCount = getManualCount(maxLottoCount)
        val autoCount: Int = manualCount.calculateAutoLottoCount()
        val lottoBundle = LottoBundle(getManualLottos(manualCount.count))
        lottoBundle.autoGenerate(autoCount, lottoGenerator)
        OutputView.printPurchasedLottoCount(manualCount.count, autoCount)
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

    private fun getManualCount(maxCount: Int): ManualCount {
        while (true) {
            OutputView.printRequestManualCount()
            val count = InputView.inputManualCount()
            var manualCount: ManualCount? = null
            val result = kotlin.runCatching { manualCount = ManualCount(count, maxCount) }
            if (result.isSuccess) return manualCount!!
            println(result.exceptionOrNull()?.message)
        }
    }

    private fun getManualLottos(manualCount: Int): MutableList<Lotto> {
        val manualLottos = mutableListOf<Lotto>()
        if (manualCount != 0) OutputView.printRequestManualLottos()
        while (manualLottos.size < manualCount) {
            manualLottos.add(getManualLotto())
        }
        return manualLottos
    }

    private fun getManualLotto(): Lotto {
        while (true) {
            val lotto: List<String> = InputView.inputManualLotto()
            var manualLotto: Lotto? = null
            val result = kotlin.runCatching { manualLotto = Lotto(lotto) }
            if (result.isSuccess) return manualLotto!!
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
