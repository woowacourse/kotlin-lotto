import controller.LottoController
import domain.Cashier
import domain.LottoDrawingMachine
import domain.LottoGenerator

fun main() {
    val controller = LottoController(Cashier(), LottoGenerator(), LottoDrawingMachine())
    controller.start()
}
