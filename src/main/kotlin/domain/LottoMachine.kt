package domain

class LottoMachine(val lottoGenerator: LottoGenerator) {

    fun generateLottos(money: Money): List<Lotto> {
        val count = money.getLottoCount()
        return (1..count).map { lottoGenerator.generateLotto() }
    }
}
