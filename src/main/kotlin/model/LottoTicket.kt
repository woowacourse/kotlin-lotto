package model

class LottoTicket(
    private val lottoTicket: List<LottoNumber>,
) {
    private val set = lottoTicket.toSet()

    init {
        require(lottoTicket.size == SIZE)
    }

    infix fun intersect(other: LottoTicket) = other.set intersect this.set

    operator fun contains(lottoNumber: LottoNumber) = lottoNumber in lottoTicket

    fun toIntList() = lottoTicket.map { it.num }

    companion object {
        const val SIZE = 6

        fun from(intList: List<Int>) = LottoTicket(intList.map { LottoNumber(it) })
    }
}
