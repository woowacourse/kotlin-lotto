package lotto.controller

import lotto.model.AutoLotteriesGenerator
import lotto.model.Count
import lotto.model.Lotto
import lotto.model.LottoGameResult
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.ManualLotteriesGenerator
import lotto.model.Money
import lotto.util.ExceptionHandler
import lotto.view.LottoGameInputView
import lotto.view.LottoGameOutputView

class LottoGameController(
    private val lottoGameInputView: LottoGameInputView,
    private val lottoGameOutputView: LottoGameOutputView,
) {
    private lateinit var lottoMachine: LottoMachine

    fun startLottoGame() {
        val purchaseExpense: Money = getPurchaseExpense()
        lottoMachine = LottoMachine(purchaseExpense)
        val manualCount = getManualCount()
        generateManualLotteries(manualCount)
        generateAutoLotteries()
        showPurchaseResult(lottoMachine.availableCount, manualCount, lottoMachine.autoLotteries)
        generateWinningLotto()
        val bonusLottoNumber = getBonusLottoNumber()
        val lottoGameResult =
            LottoGameResult(bonusLottoNumber, lottoMachine.winningLotto, lottoMachine.currentLotteries)
        showLottoGameResult(lottoGameResult, purchaseExpense)
    }

    private fun getPurchaseExpense(): Money =
        ExceptionHandler.handleInputValue {
            lottoGameInputView.inputPurchaseExpense()?.let { Money(it) } ?: getPurchaseExpense()
        }

    private fun getManualCount(): Count =
        ExceptionHandler.handleInputValue {
            lottoGameInputView.inputManualPurchaseCount()?.let {
                Count.of(it, lottoMachine.availableCount.value)
            } ?: getManualCount()
        }

    private fun generateManualLotteries(manualCount: Count) =
        ExceptionHandler.handleInputValue {
            lottoGameInputView.displayManualNumberInputMessage()
            lottoMachine.addManualLotteries(ManualLotteriesGenerator(manualCount, ::getManualLottoNumbers).generate())
        }

    private fun generateAutoLotteries() {
        lottoMachine.addAutoLotteries(AutoLotteriesGenerator(lottoMachine.availableCount).generate())
    }

    private fun getManualLottoNumbers(): List<Int> =
        ExceptionHandler.handleInputValue {
            lottoGameInputView.inputLotteryNumbers() ?: getManualLottoNumbers()
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
        ExceptionHandler.handleInputValue {
            lottoGameInputView.inputWinningNumbers()?.let {
                lottoMachine.setWinningLotto(it)
            } ?: generateWinningLotto()
        }
    }

    private fun getBonusLottoNumber(): LottoNumber =
        ExceptionHandler.handleInputValue {
            lottoGameInputView.inputBonusNumber()?.let {
                LottoNumber(it, lottoMachine.winningLotto)
            } ?: getBonusLottoNumber()
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
