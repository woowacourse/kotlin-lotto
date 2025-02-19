package lotto

class LottoFactory {
    fun generateLottoNumbers(): List<Int> {
        return (1..45).shuffled().take(6).sorted()
    }

    fun generateLottos(amount: Int): LottoResult {
        val lottos = List(amount) { Lotto(generateLottoNumbers()) }
        return LottoResult(lottos)
    }
}
