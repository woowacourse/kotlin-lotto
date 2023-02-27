import domain.Lotto
import domain.LottoBundle
import domain.LottoNumber
import domain.ManualCount
import domain.ManualLottoGenerator
import domain.Payment
import domain.RandomLottoGenerator
import domain.WinningNumbers
import view.InputView
import view.OutputView

class LottoController {

    fun startLottoGame() {
        val payment: Payment = getPayment()
        val maxLottoCount: Int = payment.calculateMaxLottoCount()
        val manualCount: ManualCount = getManualCount(maxLottoCount)
        val autoCount: Int = payment.calculateAutoLottoCount(maxLottoCount, manualCount.count)

        val manualLottos: List<Lotto> = getManualLottos(manualCount)
        val randomLottos: List<Lotto> = getRandomLottos(autoCount)
        val lottoBundle = LottoBundle(manualLottos, randomLottos)

        printPurchasedLottos(manualCount, autoCount, lottoBundle)
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

    private fun getManualLottos(manualCount: ManualCount): List<Lotto> {
        val manualLottoGenerator = ManualLottoGenerator()
        generateManualLottos(manualLottoGenerator, manualCount.count)
        return manualLottoGenerator.manualLottos
    }

    private fun generateManualLottos(manualLottoGenerator: ManualLottoGenerator, manualCount: Int) {
        if (manualCount != 0) OutputView.printRequestManualLottos()

        while (manualLottoGenerator.manualLottos.size < manualCount) {
            runCatching { manualLottoGenerator.generate(InputView.inputManualLotto()) }
                .getOrElse { error ->
                    println(error.message)
                }
        }
    }

    private fun getRandomLottos(autoCount: Int): List<Lotto> {
        val randomLottoGenerator = RandomLottoGenerator()
        randomLottoGenerator.autoGenerate(autoCount)
        return randomLottoGenerator.autoLottos
    }

    private fun printPurchasedLottos(manualCount: ManualCount, autoCount: Int, lottoBundle: LottoBundle) {
        OutputView.printPurchasedLottoCount(manualCount.count, autoCount)
        OutputView.printPurchasedLotto(lottoBundle)
    }

    private fun produceResult(lottoBundle: LottoBundle, spendPayment: Payment) {
        val winStatistics = getWinningNumbers().compareLottoBundle(lottoBundle)

        OutputView.printWinStatistics(winStatistics)
        OutputView.printEarningRate(winStatistics.calculateTotalIncome(), spendPayment)
    }

    private fun getWinningNumbers(): WinningNumbers {
        return runCatching { WinningNumbers(getWinningLotto(), getBonusNumber()) }
            .getOrElse { error ->
                println(error.message)
                getWinningNumbers()
            }
    }

    private fun getWinningLotto(): Lotto {
        OutputView.printRequestWinningNumbers()
        val winningNumbers: String = InputView.inputWinningNumbers()

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
