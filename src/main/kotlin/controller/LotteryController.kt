package controller

import WinningLottery
import model.Money
import model.lottery.Lotteries
import model.lottery.Lottery
import model.lottery.LotteryMachine
import model.lottery.LotteryNumber
import model.lottery.LotteryPrizeCalculator
import model.lottery.LotteryResultEvaluator
import model.lottery.LotterySeller
import model.profit.Profit
import model.profit.ProfitStatusDecider
import view.InputView
import view.OutputView

class LotteryController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lotteryMachine: LotteryMachine = LotteryMachine(),
    private val lotteryResultEvaluator: LotteryResultEvaluator = LotteryResultEvaluator(),
    private val profit: Profit = Profit(),
) {
    fun start() {
        val purchaseAmount = Money.from(inputView.readPurchaseAmount())
        val lotterySeller = LotterySeller(purchaseAmount)
        val lotteryCount = lotterySeller.getLotteryCount()

        val lotteries = Lotteries(List(lotteryCount) { lotteryMachine.generate() })
        outputView.showPurchasedLotteries(lotteries)

        val winningLottery =
            WinningLottery(
                Lottery.fromInput(inputView.readWinningNumbers()),
                LotteryNumber.bonusNumber(inputView.readBonusNumber()),
            )

        val winningResult =
            lotteryResultEvaluator.evaluate(lotteries, winningLottery)
        outputView.showWinningResult(winningResult)

        val totalPrizeCalculator = LotteryPrizeCalculator()
        val totalPrize = totalPrizeCalculator.calculate(winningResult)

        val profitRate = profit.calculateRate(purchaseAmount, totalPrize)
        outputView.showProfitRate(profitRate, ProfitStatusDecider.decide(purchaseAmount, totalPrize))
    }
}
