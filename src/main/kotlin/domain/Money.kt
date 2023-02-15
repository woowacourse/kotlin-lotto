package domain

@JvmInline
value class Money private constructor(val amount: Int) {

    init {
        require((amount % 1000 == 0) and (amount != 0)) { "$PREFIX 받은 돈이 1000원 단위여야합니다." }
    }

    companion object {
        const val PREFIX = "[Error]"
        fun create(amount: Int) = Money(amount)
    }
}