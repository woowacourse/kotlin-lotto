package lotto

import lotto.controller.LottoController

fun main() {
    val lottoStore = LottoController()
    lottoStore.run()
        .fold(
            onSuccess = { println("로또 실행 성공!") },
            onFailure = { println("오류 발생 ${it.message}") }
        )
}
