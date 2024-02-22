import controller.LottoController
import domain.Cashier
import domain.LottoDrawingMachine

fun main() {
    val controller = LottoController(Cashier(), LottoDrawingMachine())
    controller.start()
}
