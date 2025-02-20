package lotto.model

class LottoScanner(
    private val winningNumbers: Lotto,
) {
    fun getResult(lottos: List<Lotto>): LottoResult {
        val result = lottos.map { getRank(it) }
        return LottoResult(result)
    }

    fun getRank(lotto: Lotto): Rank {
        val countOfMatch: Int = getCountOfMatch(lotto)
        val matchBonus: Boolean = getMatchBonus(lotto)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    fun getCountOfMatch(lotto: Lotto): Int = winningNumbers.getNumbers().intersect(lotto.getNumbers().toSet()).size

    fun getMatchBonus(lotto: Lotto): Boolean = lotto.getNumbers().contains(winningNumbers.getBonusNumber())
}
