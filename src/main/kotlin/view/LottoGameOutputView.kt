package view

import model.Lotto
import model.LottoGameResult

interface LottoGameOutputView {
    fun showLottoCount(
        countOfAuto: Int,
        countOfManual: Int,
    )

    fun showPurchasedLottie(lottie: List<Lotto>)

    fun showGameResult(
        results: List<LottoGameResult.RankResult>,
        rate: Double,
    )
}
