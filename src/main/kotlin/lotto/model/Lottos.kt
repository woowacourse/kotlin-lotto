package lotto.model

data class Lottos(val publishedLottos: List<Lotto>) {
    fun makeWinningStatics(winningLotto: WinningLotto): WinningStatistics {
        return publishedLottos
            .groupBy { winningLotto.judgeRank(it) }
            .mapValues { it.value.size }
            .let(::WinningStatistics)
    }

    override fun toString(): String {
        return publishedLottos.joinToString("\n")
    }

    operator fun plus(other: Lottos): Lottos {
        return Lottos(this.publishedLottos + other.publishedLottos)
    }
}
