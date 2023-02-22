package domain

interface LottoGenerator {
    fun generateLottos(count: Int): List<Lotto>
}
