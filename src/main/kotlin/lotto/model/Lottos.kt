package lotto.model

class Lottos(
    private val manualLottos: List<Lotto>,
    private val autoLottos: List<Lotto>,
) {
    fun getTotalLottos(): List<Lotto> = manualLottos + autoLottos

    fun getAllLottoNumbers(): List<Set<Int>> {
        val totalLottos = getTotalLottos()
        return totalLottos.map { lotto ->
            val numbers = lotto.numbers
            numbers.map { it.number }.toSet()
        }
    }
}
