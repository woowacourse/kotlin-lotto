package domain

interface LottoGenerator {
    fun generateLottos(money: Money): List<Lotto>
}
