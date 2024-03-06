package controller

import ExceptionHandler
import model.Count
import model.Lotteries
import model.Lottery
import model.LotteryGenerator
import model.LotteryNumber
import model.LotteryResultEvaluator
import model.LotteryType
import model.LotteryType.Manual
import model.Money
import model.Profit
import model.ProfitRate
import model.ProfitStatusDecider
import model.WinningResult
import view.InputView
import view.OutputView
import java.math.BigDecimal

class LotteryController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lotteryResultEvaluator: LotteryResultEvaluator = LotteryResultEvaluator(),
    private val profit: Profit = Profit(),
) {
    private val lotteryGenerator = LotteryGenerator()

    fun start() {
        val purchaseAmount: Money = getPurchaseAmount()
        val lotteries = lotterySellingService(purchaseAmount)

        val winningNumbers = getWinningNumbers()
        val bonusNumber = getBonusNumber(winningNumbers)

        showResultService(lotteries, winningNumbers, bonusNumber, purchaseAmount)
    }

    private fun getPurchaseAmount(): Money {
        return ExceptionHandler.handleInputValue {
            inputView.readPurchaseAmount()?.let { Money.from(it) } ?: getPurchaseAmount()
        }
    }

    private fun lotterySellingService(purchaseAmount: Money): Lotteries {
        val count = getCount(purchaseAmount)
        val lotteries = getLotteries(purchaseAmount, count)
        outputView.showPurchasedLotteries(lotteries, count)

        return lotteries
    }

    private fun getCount(money: Money): Count {
        return ExceptionHandler.handleInputValue {
            inputView.readManualLotteryCount()?.let { Count.from(it, money) } ?: getCount(money)
        }
    }

    private fun getLotteries(
        purchaseAmount: Money,
        manualCount: Count,
    ): Lotteries {
        outputView.showManualLottoInputGuide()
        val manualLotteries = getManualLotteries(manualCount)

        val autoCount = manualCount.getAutoCount(purchaseAmount)
        val autoLotteries = getAutoLotteries(autoCount)

        return Lotteries.from(manualLotteries, autoLotteries)
    }

    private fun getManualLotteries(count: Count): Lotteries {
        return Lotteries(lotteries = List(count.amount) { getLottery() })
    }

    private fun getLottery(): Lottery {
        return ExceptionHandler.handleInputValue {
            inputView.readManualLottery()?.let { lotteryGenerator.generate(Manual, it) } ?: getLottery()
        }
    }

    private fun getAutoLotteries(count: Count): Lotteries =
        Lotteries(
            List(count.amount) {
                lotteryGenerator.generate(
                    LotteryType.Auto,
                )
            },
        )

    private fun getWinningNumbers(): Lottery = getLottery()

    private fun getBonusNumber(winningNumbers: Lottery): LotteryNumber {
        return ExceptionHandler.handleInputValue {
            inputView.readBonusNumber()?.let { LotteryNumber.bonusNumber(winningNumbers, it) } ?: getBonusNumber(
                winningNumbers,
            )
        }
    }

    private fun showResultService(
        lotteries: Lotteries,
        winningNumbers: Lottery,
        bonusNumber: LotteryNumber,
        purchaseAmount: Money,
    ) {
        val winningResult = getWinningResult(lotteries, winningNumbers, bonusNumber)
        outputView.showWinningResult(winningResult)

        val totalPrize = getTotalPrize(winningResult)
        showRateResult(purchaseAmount, totalPrize)
    }

    private fun getWinningResult(
        lotteries: Lotteries,
        winningNumbers: Lottery,
        bonusNumber: LotteryNumber,
    ): WinningResult = lotteryResultEvaluator.evaluate(lotteries, winningNumbers, bonusNumber)

    private fun getTotalPrize(winningResult: WinningResult): Money {
        var totalPrize = Money(BigDecimal(0))

        for (result in winningResult.result) {
            totalPrize += (result.key.winningPrize * result.value)
        }

        return totalPrize
    }

    private fun showRateResult(
        purchaseAmount: Money,
        totalPrize: Money,
    ) {
        val profitRate = getProfitRate(purchaseAmount, totalPrize)
        outputView.showProfitRate(profitRate, ProfitStatusDecider.decide(purchaseAmount, totalPrize))
    }

    private fun getProfitRate(
        purchaseAmount: Money,
        totalPrize: Money,
    ): ProfitRate = profit.calculateRate(purchaseAmount, totalPrize)
}
