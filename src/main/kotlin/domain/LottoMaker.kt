package domain

class LottoMaker {

    fun makeManualLottos(manualLottos: List<Lotto>): Lottos {
        return Lottos(manualLottos)
    }

    fun makeAutoLottos(count: Int): MutableList<Lotto> {
        val autoLottos = RandomLottoGenerator().generateLottos(count)
        return autoLottos.toMutableList()
    }
}
