package lotto.controller

import lotto.model.Count
import lotto.model.Lotto
import lotto.model.LottoGameResult
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.Money
import lotto.model.NumbersGenerator
import lotto.util.ExceptionHandler
import lotto.view.LottoGameInputView
import lotto.view.LottoGameOutputView

class LottoGameController(
    private val lottoGameInputView: LottoGameInputView,
    private val lottoGameOutputView: LottoGameOutputView,
    private val numbersGenerator: NumbersGenerator,
) {
    private lateinit var lottoMachine: LottoMachine

    fun startLottoGame() {
        val purchaseExpense: Money = getPurchaseExpense()
        lottoMachine = LottoMachine(purchaseExpense)
        val manualCount = getManualCount()
        generateManualLotteries(manualCount.value)
        generateAutoLotteries()
        showPurchaseResult(lottoMachine.availableCount, manualCount, lottoMachine.autoLotteries)
        generateWinningLotto()
        val bonusLottoNumber = getBonusLottoNumber()
        val lottoGameResult = LottoGameResult(bonusLottoNumber, lottoMachine.winningLotto, lottoMachine.currentLotteries)
        showLottoGameResult(lottoGameResult, purchaseExpense)
    }

    private fun getPurchaseExpense(): Money = ExceptionHandler.handleInputValue { Money(lottoGameInputView.inputPurchaseExpense()) }

    private fun getManualCount(): Count =
        ExceptionHandler.handleInputValue {
            val manualCount = lottoGameInputView.inputManualPurchaseCount()
            Count.of(manualCount, lottoMachine.availableCount.value)
        }

    private fun generateManualLotteries(manualCount: Int) {
        ExceptionHandler.handleInputValue {
            lottoMachine.addManualLotteries(lottoGameInputView.inputManualLotteryNumber(manualCount))
        }
    }

    private fun generateAutoLotteries() {
        val autoLottoNumbers = List(lottoMachine.availableCount.value) { numbersGenerator.generate() }
        lottoMachine.addAutoLotteries(autoLottoNumbers)
    }

    private fun showPurchaseResult(
        availableCount: Count,
        manualCount: Count,
        autoLotteries: List<Lotto>,
    ) {
        lottoGameOutputView.run {
            showLottoCount(availableCount.value, manualCount.value)
            showPurchasedLotteries(autoLotteries)
        }
    }

    private fun generateWinningLotto() {
        ExceptionHandler.handleInputValue { lottoMachine.setWinningLotto(lottoGameInputView.inputWinningNumbers()) }
    }

    private fun getBonusLottoNumber(): LottoNumber =
        ExceptionHandler.handleInputValue {
            LottoNumber(lottoGameInputView.inputBonusNumber(), lottoMachine.winningLotto)
        }

    private fun showLottoGameResult(
        lottoGameResult: LottoGameResult,
        purchaseExpense: Money,
    ) {
        ExceptionHandler.handleOutputValue {
            val rankResults = lottoGameResult.getWinningResult()
            val earningRate = lottoGameResult.calculateEarningRate(purchaseExpense)
            lottoGameOutputView.showGameResult(rankResults, earningRate)
        }
    }
}
