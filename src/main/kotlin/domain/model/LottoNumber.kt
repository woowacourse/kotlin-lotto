package domain.model

@JvmInline
value class LottoNumber private constructor(
    val value: Int,
) {
    override fun toString(): String = value.toString()

    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
        private val NUMBERS: Map<Int, LottoNumber> = (MINIMUM_NUMBER..MAXIMUM_NUMBER).associateWith(::LottoNumber)

        fun from(value: Int): LottoNumber = NUMBERS[value] ?: throw IllegalArgumentException()
    }
}
