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
        val payment: Payment = getPayment()
        val maxLottoCount: Int = payment.calculateMaxLottoCount()
        val manualCount: ManualCount = getManualCount(maxLottoCount)
        val autoCount: Int = payment.calculateAutoLottoCount(maxLottoCount, manualCount.count)
        val lottoBundle = LottoBundle()
        getManualLottos(lottoBundle, manualCount.count)
        lottoBundle.autoGenerate(autoCount, lottoGenerator)
        OutputView.printPurchasedLottoCount(manualCount.count, autoCount)
        OutputView.printPurchasedLotto(lottoBundle)
        produceResult(lottoBundle, payment)
    }

    private fun getPayment(): Payment {
        OutputView.printRequestMoney()
        val money = InputView.inputMoney()
        return runCatching { Payment(money) }
            .getOrElse { error ->
                println(error.message)
                getPayment()
            }
    }

    private fun getManualCount(maxCount: Int): ManualCount {
        OutputView.printRequestManualCount()
        val count = InputView.inputManualCount()
        return runCatching { ManualCount(count, maxCount) }
            .getOrElse { error ->
                println(error.message)
                getManualCount(maxCount)
            }
    }

    private fun getManualLottos(lottoBundle: LottoBundle, manualCount: Int) {
        if (manualCount != 0) OutputView.printRequestManualLottos()
        repeat(manualCount) {
            lottoBundle.manualGenerate(getManualLotto())
        }
    }

    private fun getManualLotto(): Lotto {
        val lotto: List<String> = InputView.inputManualLotto()
        return runCatching { Lotto(lotto) }
            .getOrElse { error ->
                println(error.message)
                getManualLotto()
            }
    }

    private fun produceResult(lottoBundle: LottoBundle, spendPayment: Payment) {
        val winStatistics = getWinningNumbers().compareLottoBundle(lottoBundle)

        OutputView.printWinStatistics(winStatistics)
        OutputView.printEarningRate(winStatistics.calculateTotalIncome(), spendPayment)
    }

    private fun getWinningNumbers(): WinningNumbers {
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        return runCatching { WinningNumbers(winningLotto, bonusNumber) }
            .getOrElse { error ->
                println(error.message)
                getWinningNumbers()
            }
    }

    private fun getWinningLotto(): Lotto {
        OutputView.printRequestWinningNumbers()
        val winningNumbers: List<String> = InputView.inputWinningNumbers()
        return runCatching { Lotto(winningNumbers) }
            .getOrElse { error ->
                println(error.message)
                getWinningLotto()
            }
    }

    private fun getBonusNumber(): LottoNumber {
        OutputView.printRequestBonusNumber()
        val bonusNumber: String = InputView.inputBonusNumber()
        return runCatching { LottoNumber.of(bonusNumber) }
            .getOrElse { error ->
                println(error.message)
                getBonusNumber()
            }
    }
}
