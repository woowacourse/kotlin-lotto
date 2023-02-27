package domain

class ManualLottoGenerator {
    private val _manualLottos = mutableListOf<Lotto>()
    val manualLottos: List<Lotto>
        get() = _manualLottos.toList()

    fun generate(lottoNumbers: String) {
        _manualLottos.add(Lotto(lottoNumbers))
    }
}
