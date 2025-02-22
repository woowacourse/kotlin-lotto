package lotto.model

class Lottos(
    val lottos: List<Lotto>,
) {
    fun countLottoByRank(lottoDiscriminator: LottoDiscriminator): Map<Rank, Int> {
        val countResult = Rank.entries.associateWith { 0 }.toMutableMap()

        lottos.forEach { lotto ->
            val rank = lottoDiscriminator.discriminateLotto(lotto)
            countResult[rank] = countResult.getValue(rank) + 1
        }

        return countResult
    }
}
