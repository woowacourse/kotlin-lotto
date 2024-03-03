package model

class LottoTicket(
    lottoNumbers: List<LottoNumber>,
) {
    val lottoNumberSet = lottoNumbers.toSortedSet()

    init {
        require(lottoNumberSet.size == SIZE) { NUMBER_DUPLICATED_OR_INSUFFICIENT }
    }

    infix fun intersect(other: LottoTicket) = other.lottoNumberSet intersect this.lottoNumberSet

    operator fun contains(lottoNumber: LottoNumber) = lottoNumber in lottoNumberSet

    companion object {
        const val SIZE = 6
        private const val NUMBER_DUPLICATED_OR_INSUFFICIENT = "로또 번호가 모자라거나 중복됨."

        fun from(intLottoNumbers: List<Int>) = LottoTicket(intLottoNumbers.map { LottoNumber.of(it) })
    }
}
