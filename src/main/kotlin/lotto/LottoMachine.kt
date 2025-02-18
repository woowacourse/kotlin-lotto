package lotto

class LottoMachine(
    val count: Int,
) {
    val lottos = mutableListOf<List<Int>>()

    init {
        generateLotto()
    }

    fun generateLotto() {
        repeat(count) {
            val lotto = LottoNumbers()
            lottos.add(lotto.numbers)
        }
    }
}
