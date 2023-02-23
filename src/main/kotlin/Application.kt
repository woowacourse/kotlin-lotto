import Controller.Controller
import domain.LottoGenerator
import domain.LottoMachine
import view.InputView
import view.OutputView

fun main() {
    Controller(InputView(), OutputView(), LottoMachine(LottoGenerator())).run()
}