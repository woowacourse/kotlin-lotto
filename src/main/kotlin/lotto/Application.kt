package lotto

import lotto.domain.service.LottoGenerator
import lotto.domain.service.LottoRankFinder
import lotto.view.LottoView

fun main() {
    // 의존성 주입
    val lottoView = LottoView()
    val lottoGenerator = LottoGenerator()
    val lottoRankFinder = LottoRankFinder()
    LottoController(lottoView, lottoGenerator, lottoRankFinder).run()
}
