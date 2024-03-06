import lotto.controller.LottoController
import lotto.domain.RandomLottoGenerator

fun main() {
    val randomLottoGenerator = RandomLottoGenerator()
    val controller = LottoController(randomLottoGenerator)
    controller.start()
}
