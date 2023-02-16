package domain

class Seller(private val money: Int) {

    fun getLottoCount(): Int = money / ONE_TICKET_PRICE

    companion object {
        private const val ONE_TICKET_PRICE = 1000
    }
}
