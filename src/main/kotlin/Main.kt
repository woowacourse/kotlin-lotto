import lotto.controller.LottoController
import lotto.domain.Cashier
import lotto.domain.LottoDrawingMachine

fun main() {
    val controller = LottoController(Cashier(), LottoDrawingMachine())
    controller.start()
}
