package lotto.domain.model

class Lottos(private val manualLottos: List<Lotto>, private val randomLottos: List<Lotto>) {
    val lottos get() = manualLottos + randomLottos

    fun getManualLottosCount() = manualLottos.size

    fun getRandomLottosCount() = randomLottos.size
}
