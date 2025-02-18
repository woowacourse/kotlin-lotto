package lotto

class LottoFactory {
    val lottos = mutableListOf<Lotto>()

    fun generateLottos(amount: Int): List<Lotto> {
        repeat(amount) {
            val nums = listOf(1, 2, 3, 4, 5, 6)
            lottos.add(Lotto(nums))
        }
        return lottos
    }
}
