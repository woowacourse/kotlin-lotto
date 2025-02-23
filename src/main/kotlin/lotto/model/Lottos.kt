package lotto.model

class Lottos(
    val lottos: List<Lotto>,
) {
    fun countLottoByRank(lottoRankDiscriminator: LottoRankDiscriminator): Map<Rank, Int> {
        val countResult = Rank.entries.associateWith { 0 }.toMutableMap()

        lottos.forEach { lotto ->
            val rank = lottoRankDiscriminator.discriminateLotto(lotto)
            countResult[rank] = countResult.getValue(rank) + 1
        }

        return countResult
    }
}
