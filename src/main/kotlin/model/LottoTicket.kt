package model

class LottoTicket(val lottoTicket: List<LottoNumber>) {
    init {
        require(lottoTicket.size == 6)
    }
}
