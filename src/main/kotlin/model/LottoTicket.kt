package model

class LottoTicket(
    private val lottoNumbers: List<LottoNumber>,
) {
    private val lottoNumberSet = lottoNumbers.toSet()

    init {
        require(lottoNumberSet.size == SIZE)
    }

    infix fun intersect(other: LottoTicket) = other.lottoNumberSet intersect this.lottoNumberSet

    operator fun contains(lottoNumber: LottoNumber) = lottoNumber in lottoNumbers

    fun toIntList() = lottoNumbers.map { it.num }

    companion object {
        const val SIZE = 6

        fun from(intList: List<Int>) = LottoTicket(intList.map { LottoNumber(it) })
    }
}
