package lotto

import lotto.controller.LottoController
import lotto.model.RandomLottoMachine

fun main() {
    LottoController(RandomLottoMachine).run()
}
