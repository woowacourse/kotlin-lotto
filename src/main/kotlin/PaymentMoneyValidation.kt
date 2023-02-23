import domain.PaymentMoney

class PaymentMoneyValidation {
    fun checkPaymentMoney(input: String?): Result<PaymentMoney> {
        if (input == null) return Result.failure(IllegalStateException(NULL_ERROR))
        val paymentMoney = input.toIntOrNull() ?: return Result.failure(IllegalStateException(NOT_INTEGER_ERROR))
        if (paymentMoney < PaymentMoney.ONE_LOTTO_PRICE) return Result.failure(
            IllegalStateException(
                PAYMENT_MONEY_RANGE_ERROR
            )
        )
        return Result.success(PaymentMoney(paymentMoney))
    }

    companion object {
        private const val PAYMENT_MONEY_RANGE_ERROR = "1000원 이상의 금액을 입력해주세요!!"
        private const val NOT_INTEGER_ERROR = "정수만 입력해주세요!!"
        private const val NULL_ERROR = "아무것도 입력하지 않았습니다!!"
    }
}
