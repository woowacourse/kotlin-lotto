package domain

class LottoGame(val lottos: List<Lotto>, val winningLotto: WinningLotto) {

    fun matchGame(): Map<Rank, Int> {
        return mutableMapOf<Rank, Int>().getMatchLotto(lottos, winningLotto)
    }

    private fun MutableMap<Rank, Int>.getMatchLotto(lottos: List<Lotto>, winningLotto: WinningLotto): Map<Rank, Int> {

        lottos.forEach {
            val rank = it.matchLotto(winningLotto)
            if (!rank.equals(Rank.MISS)) this.put(rank, this.getOrDefault(rank, 0) + 1)
        }
        return this
    }
}
