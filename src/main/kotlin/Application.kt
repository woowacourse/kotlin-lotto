import controller.LotteryController
import model.lottery.LotteryMachine
import model.lottery.LotterySeller
import model.profit.Profit
import view.InputView
import view.OutputView

fun main() {
    LotteryController(
        InputView(),
        OutputView(),
        LotterySeller(),
        LotteryMachine(),
        Profit(),
    ).start()
}
