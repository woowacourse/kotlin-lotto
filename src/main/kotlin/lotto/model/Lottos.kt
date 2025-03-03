package lotto.model

class Lottos(
    private val manualLottos: List<Lotto>,
    private val autoLotts: List<Lotto>,
) {
    val lottoBundle = manualLottos + autoLotts

    val size: Int = lottoBundle.size

    fun calculateLottoResult(winningLotto: WinningLotto): LottoResult {
        val ranks: Map<Rank, Int> =
            lottoBundle
                .groupingBy { lotto -> winningLotto.getRank(lotto) }
                .eachCount()

        return LottoResult(ranks)
    }
}
