package model.lottery

import model.Money
import model.Quantity
import java.text.DecimalFormat

class LotterySeller {
    fun getLotteryQuantity(money: Money): Quantity {
        require(money % PRICE == Money.ZERO) { indicateErrorMoney(money) + "1,000 원 단위로 입력하세요." }
        require(money in PRICE..MAX_PURCHASE_AMOUNT) {
            indicateErrorMoney(money) +
                "${decimalFormat.format(MIN_PRICE_AMOUNT)}원 이상, ${decimalFormat.format(MAX_PRICE_AMOUNT)}원 이하로만 구매가 가능합니다."
        }
        return Quantity((money / PRICE).toInt())
    }

    companion object {
        private const val MIN_PRICE_AMOUNT = 1_000
        private const val MAX_PRICE_AMOUNT = 100_000

        private val decimalFormat = DecimalFormat("#,###")

        private val PRICE = Money.wons(MIN_PRICE_AMOUNT)
        private val MAX_PURCHASE_AMOUNT = Money.wons(MAX_PRICE_AMOUNT)

        private fun indicateErrorMoney(money: Money) = "${money.amount}를 입력하셨습니다. "
    }
}
