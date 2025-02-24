package lotto.model

class Lottos(
    val lottoBundle: List<Lotto>,
) {
    val size: Int
        get() = lottoBundle.size

    fun calculateLottoResult(winningLotto: WinningLotto): LottoResult {
        val ranks =
            lottoBundle
                .groupingBy { winningLotto.findLottoRank(it) }
                .eachCount()
        return LottoResult(ranks)
    }
}
