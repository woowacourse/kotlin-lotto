package lotto.domain.model

class Lottos {
    private val randomLottos: MutableList<Lotto> = mutableListOf()
    private val manualLottos: MutableList<Lotto> = mutableListOf()
    val lottos get() = manualLottos + randomLottos

    fun addRandomLotto(lotto: Lotto) {
        randomLottos.add(lotto)
    }

    fun addManualLotto(lotto: Lotto) {
        manualLottos.add(lotto)
    }

    fun getRandomLottosSize() = randomLottos.size

    fun getManualLottosSize() = manualLottos.size
}
