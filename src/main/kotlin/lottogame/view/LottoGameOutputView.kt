package lottogame.view

import lottogame.model.Lotto
import lottogame.model.LottoGameResult

interface LottoGameOutputView {
    fun showPurchasedLottie(lottie: List<Lotto>)

    fun showPurchaseLotto(
        manualLottie: List<Lotto>,
        autoLottie: List<Lotto>,
    )

    fun showGameResult(
        results: List<LottoGameResult.RankResult>,
        rate: Double,
    )
}
