data class LottoNumber(val number: Int) : Comparable<LottoNumber> {
    companion object {
        private val lottoNumbers: List<LottoNumber> = (0..45).map { LottoNumber(it) }

        fun of(number: Int): LottoNumber {
            return lottoNumbers[number]
        }
    }

    override fun compareTo(other: LottoNumber): Int {
        return this.number - other.number
    }
}
