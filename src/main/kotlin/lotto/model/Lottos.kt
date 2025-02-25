package lotto.model

class Lottos(
    val lottoBundle: List<Lotto>,
) {
    val size: Int
        get() = lottoBundle.size

    fun calculateLottoResult(winningLotto: WinningLotto): LottoResult {
        val ranks =
            lottoBundle
                .groupingBy { lotto -> winningLotto.findLottoRank(lotto) }
                .eachCount()
        return LottoResult(ranks)
    }
}
