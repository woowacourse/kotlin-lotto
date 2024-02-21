package view

import model.Lotto

object OutputView {

    fun printLottoQuantity(quantity: Int) {
        println("${quantity}개를 구매했습니다.")
    }

    fun printLottoNumbers(lottoTickets: List<Lotto>) {
        lottoTickets.forEach {
            println(it.numbers.map { it.value }.joinToString(prefix = "[", separator = ", ", postfix = "]"))
        }
    }
}
