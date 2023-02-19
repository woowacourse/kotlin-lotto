package domain

class LottoMachine(val lottoGenerator: LottoGenerator) {

    fun generateLottos(money: Money): List<Lotto> {
        val count = money.getLottoCount()
        return mutableListOf<Lotto>().getLottos(count)
    }

    private fun MutableList<Lotto>.getLottos(count: Int): List<Lotto> {
        repeat(count) {this.add(lottoGenerator.generateLotto())}
        return this
    }
}