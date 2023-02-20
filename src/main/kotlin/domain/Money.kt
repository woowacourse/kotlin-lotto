package domain

@JvmInline
value class Money private constructor(val amount: Long) {

    init {
        require((amount % 1000L == 0L) and (amount != 0L)) { "$PREFIX $amount 받은 돈이 1000원 단위여야합니다." }
    }

    companion object {
        private const val PREFIX = "[Error]"
        fun create(amount: Long) = Money(amount)
        fun create(amount: Int) = Money(amount.toLong())
    }
}
