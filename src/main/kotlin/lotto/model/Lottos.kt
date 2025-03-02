package lotto.model

class Lottos(
    private val passiveLottos: List<Lotto>,
    private val activeLottos: List<Lotto>,
) {
    fun getTotalLottos(): List<Lotto> = passiveLottos + activeLottos

    fun getAllLottoNumbers(): List<Set<Int>> {
        val totalLottos = getTotalLottos()
        return totalLottos.map { lotto ->
            val numbers = lotto.numbers
            numbers.map { it.number }.toSet()
        }
    }
}
