package lotto

import lotto.service.LottoGenerator
import lotto.view.LottoView

fun main() {
    // 의존성 주입
    val lottoView = LottoView()
    val lottoGenerator = LottoGenerator()
    LottoController(lottoView, lottoGenerator).run()
}
