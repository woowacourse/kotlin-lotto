package lotto

class LottoFactory {
    val lottos = mutableListOf<Lotto>()

    fun generateLottos(amount: Int): List<Lotto> {
        repeat(amount) {
            val nums: List<Int> = generateLottoNumbers()
            lottos.add(Lotto(nums))
        }
        return lottos
    }

    fun generateLottoNumbers(): List<Int> {
        val lottoNumber = (1..45).shuffled().take(6).sorted()
        return lottoNumber
    }
}
