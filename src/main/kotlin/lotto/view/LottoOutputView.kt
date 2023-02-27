package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.Rank

interface LottoOutputView {
    fun printLottoCountMessage(manualCount: Int, autoCount: Int)
    fun printLottoNumbers(lottoNumbers: List<Lotto>)
    fun printResult(ranks: List<Rank>, yield: Double)
}
