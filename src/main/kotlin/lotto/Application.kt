package lotto

import kotlin.random.Random

fun main() {
    //의존성 주입
    val lottoView = LottoView()
    val lottoService = LottoService(Random(12943581))
    LottoController(lottoView, lottoService).run()
}
