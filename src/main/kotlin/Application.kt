import domain.LottoGenerator
import domain.LottoMachine
import view.InputView
import view.OutputView

fun main() {
    LottoGameController(InputView(), OutputView(), LottoMachine(LottoGenerator())).run()
}
