package model

@JvmInline
value class LottoNumber(private val num: Int) : Comparable<LottoNumber> {
    init {
        require(num in RANGE)
    }

    companion object {
        private const val MIN = 1
        private const val MAX = 45
        val RANGE = MIN..MAX
    }

    override fun compareTo(other: LottoNumber): Int = other.num - this.num
}
