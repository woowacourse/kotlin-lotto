package lotto.model

class LottoWallet {
    private val lottoBundle: MutableList<Lotto> = mutableListOf()

    fun add(lottoNumbers: List<Int>) {
        lottoBundle.add(Lotto.from(lottoNumbers))
    }

    fun addAll(lottos: List<Lotto>) {
        lottoBundle.addAll(lottos)
    }

    fun get() = lottoBundle.toList()
}
