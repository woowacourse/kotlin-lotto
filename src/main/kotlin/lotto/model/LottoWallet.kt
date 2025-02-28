package lotto.model

class LottoWallet {
    private val _lottos: MutableList<Lotto> = mutableListOf()
    val lottos: List<Lotto> get() = _lottos.toList()

    fun addAll(lottos: List<Lotto>) {
        _lottos.addAll(lottos)
    }
}
