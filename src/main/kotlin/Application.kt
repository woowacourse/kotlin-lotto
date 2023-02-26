import controller.LottoController
import view.InputView
import view.OutputView

fun main() {
    val t = Thread(LottoController(InputView(), OutputView()))
    t.setUncaughtExceptionHandler { _, e ->
        println("\n" + e.message)
        println("예상하지 못한 예외가 발생하여 프로그램을 종료합니다.")
    }
    t.start()
}
