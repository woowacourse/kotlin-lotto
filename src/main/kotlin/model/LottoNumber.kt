package model

@JvmInline
value class LottoNumber private constructor(private val num: Int) : Comparable<LottoNumber> {
    init {
        require(num in RANGE)
    }

    override fun compareTo(other: LottoNumber): Int = this.num - other.num

    override fun toString(): String = num.toString()

    companion object {
        private const val MIN = 1
        private const val MAX = 45
        val RANGE = MIN..MAX
        private val cache = mutableMapOf<Int, LottoNumber>()

        fun of(num: Int) = cache.getOrPut(num) { LottoNumber(num) }
    }
}
