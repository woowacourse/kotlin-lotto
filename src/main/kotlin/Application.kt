import controller.LotteryController
import model.lottery.LotterySeller
import view.InputView
import view.OutputView

fun main() {
    LotteryController(
        InputView(),
        OutputView(),
        LotterySeller(),
    ).start()
}
