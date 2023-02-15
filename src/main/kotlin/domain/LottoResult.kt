package domain

data class LottoResult(private val result: Map<Rank, Int>) : Map<Rank, Int> by result {
    companion object {
        fun of(lottos: List<Lotto>, winningLotto: WinningLotto): LottoResult =
            LottoResult(
                Rank.values().associateWith { rank ->
                    lottos.count { rank == Rank.valueOf(winningLotto.getCountOfMatch(it), winningLotto.matchBonus(it)) }
                },
            )
    }
}
