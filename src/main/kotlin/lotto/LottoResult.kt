package lotto

class LottoResult(val amount: Int) {
    val lottos = mutableListOf<Lotto>()

    fun generateLottos(): List<Lotto> {
        repeat(amount) {
            val nums: List<Int> = LottoFactory().generateLottoNumbers()
            lottos.add(Lotto(nums))
        }
        return lottos
    }
}
