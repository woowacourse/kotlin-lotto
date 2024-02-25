import lotto.controller.LottoController
import lotto.domain.Cashier
import lotto.domain.RandomLottoGenerator

fun main() {
    val randomLottoGenerator = RandomLottoGenerator()
    val controller = LottoController(Cashier(), randomLottoGenerator)
    controller.start()
}
