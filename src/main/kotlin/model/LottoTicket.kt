package model

class LottoTicket(val lottoTicket: List<LottoNumber>) {
    init {
        require(lottoTicket.size == SIZE)
    }

    companion object {
        const val SIZE = 6

        fun from(intList: List<Int>) = LottoTicket(intList.map { LottoNumber(it) })
    }
}
