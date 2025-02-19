package lotto

class LottoFactory() {
    val lottos = mutableListOf<Lotto>()

    fun generateLottoNumbers(): List<Int> {
        val lottoNumber = (1..45).shuffled().take(6).sorted()
        return lottoNumber
    }

    fun generateLottos(amount: Int): List<Lotto> {
        repeat(amount) {
            val nums: List<Int> = LottoFactory().generateLottoNumbers()
            lottos.add(Lotto(nums))
        }
        return lottos
    }
}
