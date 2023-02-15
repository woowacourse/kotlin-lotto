class Lottos(private val lottos: List<Lotto>) {

    fun getTotalLottoResults(winningNumbers: WinningNumbers) = lottos.map { lotto ->
        lotto.getLottoResult(winningNumbers)
    }
}
