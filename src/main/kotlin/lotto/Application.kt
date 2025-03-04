package lotto

import lotto.view.LottoView

fun main() {
    // 의존성 주입
    val lottoView = LottoView()
    LottoController(lottoView).run()
}
