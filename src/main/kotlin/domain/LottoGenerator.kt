package domain

interface LottoGenerator {
    fun generateLottos(count: Int): List<Lotto>

    fun generateLottos(manualLottos: List<List<Int>>): List<Lotto>
}
