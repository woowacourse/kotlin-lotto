package controller
import model.Money
import model.lottery.Lotteries
import model.lottery.Lottery
import model.lottery.LotteryMachine
import model.lottery.LotteryNumber
import model.lottery.LotteryResultEvaluator
import model.lottery.LotterySeller
import model.profit.Profit
import model.profit.ProfitStatusDecider
import view.InputView
import view.OutputView

class LotteryController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lotteryResultEvaluator: LotteryResultEvaluator = LotteryResultEvaluator(),
    private val profit: Profit = Profit(),
) {
    fun start() {
        val purchaseAmount = Money.from(inputView.readPurchaseAmount())
        val lotterySeller = LotterySeller(purchaseAmount)
        val lotteryCount = lotterySeller.getLotteryCount()

        val lotteryMachine = LotteryMachine()

        val lotteries = Lotteries(List(lotteryCount) { lotteryMachine.generate() })
        outputView.showPurchasedLotteries(lotteries)

        val winningNumbers = Lottery.fromInput(inputView.readWinningNumbers())
        val bonusNumber = LotteryNumber.bonusNumber(winningNumbers, inputView.readBonusNumber())

        val winningResult = lotteryResultEvaluator.evaluate(lotteries, winningNumbers, bonusNumber)
        outputView.showWinningResult(winningResult)

        val totalPrize =
            Money.wons(
                winningResult.result.entries.sumOf {
                    it.key.winningPrize.amount.toInt() * it.value.count
                },
            )

        val profitRate = profit.calculateRate(purchaseAmount, totalPrize)

        outputView.showProfitRate(profitRate, ProfitStatusDecider.decide(purchaseAmount, totalPrize))
    }
}
