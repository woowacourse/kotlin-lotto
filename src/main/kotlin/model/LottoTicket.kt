package model

class LottoTicket(
    lottoNumbers: List<LottoNumber>,
) {
    val lottoNumberSet = lottoNumbers.toSortedSet()

    init {
        require(lottoNumberSet.size == SIZE)
    }

    infix fun intersect(other: LottoTicket) = other.lottoNumberSet intersect this.lottoNumberSet

    operator fun contains(lottoNumber: LottoNumber) = lottoNumber in lottoNumberSet

    companion object {
        const val SIZE = 6

        fun from(intList: List<Int>) = LottoTicket(intList.map { LottoNumber.of(it) })
    }
}
