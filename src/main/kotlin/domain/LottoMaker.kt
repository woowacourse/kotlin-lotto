package domain

class LottoMaker {
    fun wrapLotto(number: List<Int>): Lotto {
        return Lotto(number.map { LottoNumber.from(it) })
    }

    fun makeManualLottos(manualLottos: List<Lotto>): Lottos {
        return Lottos(manualLottos)
    }

    fun makeAutoLottos(count: Int): MutableList<Lotto> {
        val autoLottos = RandomLottoGenerator().generateLottos(count)
        return autoLottos.toMutableList()
    }
}
