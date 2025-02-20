package lotto

class LottoFactory {
    fun generateLottoNumbers(): List<Int> {
        return (1..45).shuffled().take(6).sorted()
    }

    fun generateLottos(amount: Int): List<Lotto> {
        return List(amount) { Lotto(generateLottoNumbers()) }
    }
}
