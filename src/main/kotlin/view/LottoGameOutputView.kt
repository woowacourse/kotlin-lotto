package view

import model.Lotto
import model.LottoGameResult

interface LottoGameOutputView {
    fun showPurchasedLottie(lottie: List<Lotto>)

    fun showGameResult(results: List<LottoGameResult.RankResult>)

    fun showEarningRate(rate: Double)
}
