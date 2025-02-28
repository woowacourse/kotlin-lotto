package lotto.model

class Lottos(
    val manualLottoBundle: List<Lotto> = emptyList(),
    val automaticLottoBundle: List<Lotto> = emptyList(),
) {
    fun getManualLottoCount(): LottoCount = LottoCount(manualLottoBundle.size)

    fun getAutomaticLottoCount(): LottoCount = LottoCount(automaticLottoBundle.size)

    fun getAllLottoBundle(): List<Lotto> = manualLottoBundle + automaticLottoBundle
}
