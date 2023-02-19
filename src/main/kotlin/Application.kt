import controller.LottoController
import view.InputView
import view.OutputView

fun main() {
    LottoController(InputView(), OutputView()).run()
}
