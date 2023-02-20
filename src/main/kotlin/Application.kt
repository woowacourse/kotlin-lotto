import controller.LottoController
import view.InputView
import view.OutputView

fun main() {
    runCatching {
        val lottoController = LottoController(InputView(), OutputView())
        lottoController.run()
    }.onFailure {
        println(it.message)
    }
}
