import controller.LotteryController
import model.lottery.LotteryMachine
import model.lottery.LotteryResultEvaluator
import model.lottery.LotterySeller
import model.profit.Profit
import model.profit.ProfitStatusDecider
import view.InputView
import view.OutputView

fun main() {
    LotteryController(
        InputView(),
        OutputView(),
        LotterySeller(),
        LotteryMachine(),
        LotteryResultEvaluator(),
        Profit(),
        ProfitStatusDecider(),
    ).start()
}
