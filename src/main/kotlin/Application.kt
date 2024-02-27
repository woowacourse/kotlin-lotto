import controller.LotteryController
import model.lottery.LotteryMachine
import model.lottery.LotteryRankEvaluator
import model.lottery.LotteryResultEvaluator
import model.lottery.LotterySeller
import model.lottery.LottoBonusNumberChecker
import model.lottery.LottoNumberComparator
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
        LotteryResultEvaluator(LotteryRankEvaluator(LottoNumberComparator(), LottoBonusNumberChecker())),
        Profit(),
        ProfitStatusDecider(),
    ).start()
}
