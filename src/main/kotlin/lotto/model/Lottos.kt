package lotto.model

class Lottos(
    private val manualLottos: List<Lotto>,
    private val autoLotts: List<Lotto>,
) {
    val lottoBundle = manualLottos + autoLotts

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
