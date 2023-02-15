package domain.lotto

class PurchasedLotto(lottoNumbers: List<LottoNumber>) : Lotto(lottoNumbers) {
    fun getSortedLotto(): Lotto = Lotto(this.sortedBy { it.value })
}
