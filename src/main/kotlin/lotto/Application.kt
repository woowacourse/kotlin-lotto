package lotto

import lotto.global.Config.RANDOM_SEED
import kotlin.random.Random

fun main() {
    // 의존성 주입
    val lottoView = LottoView()
    val lottoService = LottoService(Random(RANDOM_SEED))
    LottoController(lottoView, lottoService).run()
}
