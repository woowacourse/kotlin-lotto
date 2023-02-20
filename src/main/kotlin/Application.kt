import controller.LottoController
import view.InputView
import view.ResultView

fun main() {
    LottoController(InputView(), ResultView()).run()
}
