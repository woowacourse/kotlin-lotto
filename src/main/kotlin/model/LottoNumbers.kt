package model

class LottoNumbers (val lottoTicket: List<Int>) {
    init {
        require(lottoTicket.size == 6)
        require(this.lottoTicket.all {it in 1..45})
    }
}