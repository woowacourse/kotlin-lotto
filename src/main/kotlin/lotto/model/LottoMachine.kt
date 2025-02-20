package lotto.model

class LottoMachine(
    val count: Int,
) {
    val lottos: List<Lotto> = generateLottos(count)

    companion object {
        private fun generateLottos(count: Int): List<Lotto> {
            val lottos = mutableListOf<Lotto>()
            repeat(count) {
                lottos.add(Lotto())
            }
            return lottos
        }
    }
}
