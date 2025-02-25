package lotto

import lotto.service.LottoGenerator
import lotto.service.LottoRankFinder
import lotto.view.LottoView

fun main() {
    // 의존성 주입
    val lottoView = LottoView()
    val lottoGenerator = LottoGenerator()
    val lottoRankFinder = LottoRankFinder()
    LottoController(lottoView, lottoGenerator, lottoRankFinder).run()
}
