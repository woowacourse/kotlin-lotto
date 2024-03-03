package lotto.view

import lotto.model.Lotto
import lotto.model.LottoGameResult

interface LottoGameOutputView {
    fun showLottoCount(
        countOfAuto: Int,
        countOfManual: Int,
    )

    fun showPurchasedLotteries(lotteries: List<Lotto>)

    fun showGameResult(
        results: List<LottoGameResult.RankResult>,
        rate: Double,
    )
}
